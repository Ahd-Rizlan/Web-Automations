package pages;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;
import utils.constants.PawnConstants;

import java.util.Arrays;
import java.util.List;

public class LoanAccountDetailedViewPage extends BasePage {

    String loanAccounttNumber,grantedDate,installmentsLeft,capitalInstallment,expireDate,overdueAmount,OutStandingamountOnly,outStandingAmountcurrencyPart,amount,charges,total;
    String[] cardCount;
    double ExpectedOutstandingAmountAfter;
    public LoanAccountDetailedViewPage (WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblLoanAccountSummary = By.xpath("//span[contains(text(),'Loan Account Summary')]");

    private static final By lblLoans = By.xpath("(//a[@class='SubMenu_subMenuItem___oYCo'])[4]");
    private static final By imgFDicon = By.xpath("//img[contains(@src, '2FNewLoanIcon')]");
   private static final By icnAccounts = By.xpath("//div[contains(@class,'flex flex-col items-center')]/div[3]/div[1]");
   // private static final By icnAccounts = By.xpath("(//div[contains(@class,'flex flex-col items-center')]/div[1])[2]");
    private static final By lblFDnumberamdAmount = By.xpath("//div[@class='flex']/span[2]");
    private static final By lblLoanCard = By.xpath("//div[contains(@class, 'items-center justify-center flex ')]");
    private static final By lblOutStandingAmount = By.xpath("//span[@class='text-black']");
    private static final By btnRightArrow = By.xpath("//img[contains(@src, 'CardArrowRight')]");
    private static final By btnLeftArrow = By.xpath("//img[contains(@src, 'CardArrowLeft')]");
    private static final By lblPawningCardOutStandingAmount = By.xpath("//div[@class='text-2xl']/span[@class='text-black']");
    private static final By lblDuration = By.xpath("//div[contains(@class,'flex justify-between text-xs')]");
    private static final By btnSettleLoan = By.xpath("(//img[contains(@src, 'FMyAccountCheckBookRequest')])[2]");
    private static final By btnSubmit = By.xpath("//button[contains(@class, 'items-center justify-center disabled:opacity')]");
    private static final By ddDebitedAccount = By.xpath("//select[@id='accountfrom']");
    private static final By txtCustomAmountAmount = By.xpath("//input[@inputmode='numeric']");
    private static final By rdAgreement = By.xpath("//input[@type='checkbox']");
    private static final By btnSettlement = By.xpath("//button[contains(@class, 'items-center justify-center flex disabled')]");
    private static final By lblOutStandngAmount = By.xpath("//span[@class='text-black']");
    private static final By btnPartialInstallments = By.xpath("(//div[contains(@class, 'cursor-pointer text')])[2]");
    private static final By btnPaidInstallments = By.xpath("(//div[contains(@class, 'cursor-pointer text')])[1]");
    private static final By tblPartialInstallments = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr");
    private static final By lblLoanAccountNumber = By.xpath("//span[contains(@class, 'flex') and contains(., 'Current Outstanding')]");

   // private static final By lblLoanAccountNumber = By.xpath("//span[contains(@class, 'flex')]/text()[1]");
    private static final By lblOverDue = By.xpath("//li[contains(text(), 'Overdue Amount')]/span");
    private static final By lblSavingsPrimaryStatus = By.xpath("//div[contains(@class,'justify-center items-center text-center')]");
    private static final By lblPawningAccount = By.xpath("//div[contains(@class, 'flex flex-col pt-4 pl-4 gap-1')]//span[contains(@class, 'false')]");
    private static final By lblPawningInterest = By.xpath("//div[contains(@class, 'flex flex-col')]//span[contains(@class, 'text-end')]");
    private static final By tblPawningList = By.xpath("//tr[contains(@class, 'overflow-hidden shadow-md shadow-gray-300 ')]");
    private static final By tblPawningHistoryList = By.xpath("//table[contains(@class, 'border-separate border-spacing')]");
    private static final By btnNext = By.xpath("//button[contains(@class, 'text-white  min-w')]");
    private static final By txtErrorMessage = By.xpath("//span[@class='text-red-500 text-xs']");
    private static final By lblPawningConfirmation = By.xpath("//span[@class='text-sm font-bold']");
    private static final By lblOutStandingPawningAmount = By.xpath("//div[@class='text-2xl']/span[@class='text-black']");

    public By getLoanDetailValueByLabel(String label) {
        return By.xpath("//ul[contains(@class,'list-disc')]//li[contains(text(), '" + label + "')]/span");
    }
    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By getSummaryValueByLabel(String label) {
        return By.xpath("//div[@class='flex flex-col space-y-2']//div[contains(@class,'justify-between')][div[text()='" + label + "']]/div[2]");
    }

    private static By tblCellRecord(int row, int col) {
        return By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[" + row + "]/td[" + col + "]");
    }

    /**
     * This method will navigate to the Loan Detail section
     */

    public void NavogatetoPawningPage() {

        addToReport("----------Navigating to the Loan section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(driver, imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS,false);

        waitForElementPresence(lblLoans);
        clickOnElement(lblLoans);
        addToReport("Clicked on the Loan tab ", Status.PASS);

        if (waitForElementPresence(lblOverDue, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport("Loan details are visible.", Status.PASS);
        } else {
            addToReport("Loan details are not visible.", Status.FAIL);
        }

        addToReport("----------Navigated to the Loan section ----------", Status.INFO, false);

    }

    public void ValidatingLoanDetails(String[] currencyType, String errorMsg){


        //Obtain pagination value
        cardCount=CommonUtils.splitText(getAttributeOrText(icnAccounts,"text"),"/");

        //Obtain the accounts record count
        int recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {


            for (int inc = 0; inc < recordCount; inc++) {

                waitForElementPresence(lblPawningCardOutStandingAmount,PawnConstants.WAIT_MEDIUM);
                outStandingAmountcurrencyPart  = getTextFromElement(lblPawningCardOutStandingAmount).trim().split(" ")[0]; //LKR
                // Compare with constant
                if (Arrays.asList(currencyType).contains(outStandingAmountcurrencyPart)) {
                    addToReport("Currency Type is: " + outStandingAmountcurrencyPart, Status.PASS, false);
                } else {
                    addToReport("Currency mismatch. Expected: " + Arrays.toString(currencyType) + ", Found: " + outStandingAmountcurrencyPart, Status.FAIL);
                }

                String[] loanParts = getTextFromElement(lblLoanAccountSummary).trim().split(":");
                if (loanParts.length == 2) {
                    loanAccounttNumber = loanParts[1].trim();
                    addToReport("Loan Account Number: '" + loanAccounttNumber + "'", Status.PASS, false);
                } else {
                    addToReport("Loan Account summary format is invalid: '" + Arrays.toString(loanParts) + "'", Status.FAIL);
                    loanAccounttNumber = ""; // fallback to prevent null pointer if used later
                }
                grantedDate = getTextFromElement(getLoanDetailValueByLabel("Loan Granted Date")).trim();
                installmentsLeft = getTextFromElement(getLoanDetailValueByLabel("Installments Left")).trim();
                capitalInstallment = getTextFromElement(getLoanDetailValueByLabel("Capital Installment Amount")).trim();
                expireDate = getTextFromElement(getLoanDetailValueByLabel("Loan Expire Date")).trim();
                overdueAmount = getTextFromElement(getLoanDetailValueByLabel("Overdue Amount")).trim();

                boolean flag = true;


//=== Loan account number ===
                if (CommonUtils.containsNumericCharacters(loanAccounttNumber)) {
                    addToReport("Successfully validated Loan Account Number: '" + loanAccounttNumber + "'", Status.PASS, false);
                } else {
                    addToReport("Loan Account Number is invalid: '" + loanAccounttNumber + "'", Status.FAIL);
                }
// === Loan Granted Date ===
                if (CommonUtils.containsValuesOnDate(grantedDate)) {
                    addToReport("Successfully validated Loan Granted Date: '" + grantedDate + "'", Status.PASS, false);
                } else {
                    addToReport("Loan Granted Date is not valid: '" + grantedDate + "'", Status.FAIL);
                    flag = false;
                }

// === Installments Left ===
                if (CommonUtils.containsNumericCharacters(installmentsLeft)) {
                    addToReport("Successfully validated Installments Left: '" + installmentsLeft + "'", Status.PASS, false);
                } else {
                    addToReport("Installments Left is not valid: '" + installmentsLeft + "'", Status.FAIL);
                    flag = false;
                }

// === Capital Installment Amount ===
                if (CommonUtils.containsNumericCharactersWithNegativeValues(capitalInstallment.replace("LKR", "").replace(",", "").trim())) {
                    addToReport("Successfully validated Capital Installment Amount: '" + capitalInstallment + "'", Status.PASS, false);
                } else {
                    addToReport("Capital Installment Amount is not valid: '" + capitalInstallment + "'", Status.FAIL);
                    flag = false;
                }

// === Loan Expire Date ===
                if (CommonUtils.containsValuesOnDate(expireDate)) {
                    addToReport("Successfully validated Loan Expire Date: '" + expireDate + "'", Status.PASS, false);
                } else {
                    addToReport("Loan Expire Date is not valid: '" + expireDate + "'", Status.FAIL);
                    flag = false;
                }

// === Overdue Amount ===
                if (CommonUtils.containsNumericCharactersWithNegativeValues(overdueAmount.replace("LKR", "").replace(",", "").trim())) {
                    addToReport("Successfully validated Overdue Amount: '" + overdueAmount + "'", Status.PASS, false);
                } else {
                    addToReport("Overdue Amount is not valid: '" + overdueAmount + "'", Status.FAIL);
                    flag = false;
                }

                if (isElementPresentBy(lblFDnumberamdAmount)) {
                    String[] fdAccountNumber = getTextFromElement(lblFDnumberamdAmount).trim().split("\\|");
                    if (fdAccountNumber.length == 2) {
                        addToReport("Fixed Deposit Details - Number: '" + fdAccountNumber[0].trim() + "', Amount: '" + fdAccountNumber[1].trim() + "'", Status.PASS, false);
                    } else {
                        addToReport("Fixed Deposit text format is invalid: " + Arrays.toString(fdAccountNumber), Status.FAIL);
                    }
                } else {
                    addToReport("Fixed Deposit Details unavailable.", Status.PASS);
                }


// === Final Flag Check ===
                if (flag) {
                    addToReport("All loan summary fields validated successfully.", Status.PASS);
                } else {
                    addToReport("Some loan summary fields failed validation.", Status.FAIL);
                }

                //Navigate to next account
                clickOnElement(btnRightArrow);
            }

        }
        else {
            addToReport("No accounts found", Status.FAIL);
            throw new RuntimeException("Error - No accounts found");
        }


        int recordCountBackWards = Integer.parseInt(cardCount[0]);
        if (recordCountBackWards != 0) {

            // Get currency of current card first
            outStandingAmountcurrencyPart = getTextFromElement(lblPawningCardOutStandingAmount).trim().split(" ")[0];

            if (outStandingAmountcurrencyPart.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[1])) {
                clickOnElement(lblLoanCard);
                addToReport("Clicked on the dollar account: " + outStandingAmountcurrencyPart, Status.PASS, false);

            } else {
                for (int inc = recordCountBackWards; inc >= 1; inc--) {
                    clickOnElement(btnLeftArrow); // Move to previous card
                    outStandingAmountcurrencyPart = getTextFromElement(lblPawningCardOutStandingAmount).trim().split(" ")[0]; // Update the value

                    if (outStandingAmountcurrencyPart.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[1])) {
                        clickOnElement(lblLoanCard);
                        addToReport("Clicked on the dollar account: " + outStandingAmountcurrencyPart, Status.PASS, false);
                        break;
                    } else {
                        addToReport("No dollar account found, Currency mismatch. Expected: " + DashboardConstants.CURRENCY_VALUES[1] + ", Found: " + outStandingAmountcurrencyPart, Status.FAIL);
                    }
                }
            }

        } else {
            addToReport("No cards available to search for dollar account.", Status.FAIL);
        }

        waitForElementPresence(lblDuration,20);
        addToReport("Loan duration is visible", Status.PASS, false);

        clickOnElement(btnSettleLoan);
        waitForElementPresence(getSuccessfulMsg(errorMsg),20);
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(errorMsg))) {
            addToReport("'" + errorMsg + "' message is present.", Status.PASS,true);
        } else {
            addToReport("'" + errorMsg + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }


    }

    public void ValidatethePartialSettlement(String loanAccountNumber,String accountNumber, String customAmount,String successMsg) {


        String firstCardNumber = "";
        boolean found = false;

        int recordCountBackWards = Integer.parseInt(cardCount[1]);
        if (recordCountBackWards == 0) {
            addToReport("No cards available to search for loan account.", Status.FAIL);
            return;
        }

        for (int i = 0; i < recordCountBackWards; i++) {

            waitForElementPresence(lblLoanAccountNumber, PawnConstants.WAIT_LONG);
            String cardNumberRaw = getTextFromElement(lblLoanAccountNumber);
            if (cardNumberRaw == null || cardNumberRaw.trim().isEmpty()) {
                addToReport("Loan account number element not found or empty", Status.FAIL);
                break;
            }

            String currentLoanNumber = cardNumberRaw.trim().replaceAll("[^0-9]", "");

            if (i == 0) {
                firstCardNumber = currentLoanNumber; // Set it only at the start
            } else if (currentLoanNumber.equals(firstCardNumber)) {
                addToReport("Looped through all cards. Target loan account not found: " + loanAccountNumber, Status.FAIL);
                break;
            }

            if (currentLoanNumber.equalsIgnoreCase(loanAccountNumber)) {
                clickOnElement(lblLoanCard);
                addToReport("Clicked on the matching account: " + currentLoanNumber, Status.PASS, false);
                found = true;
                break;
            } else {
                addToReport("Account mismatch. Expected: " + loanAccountNumber + ", Found: " + currentLoanNumber, Status.INFO);
                clickOnElement(btnLeftArrow);
                waitFor(5); // Let card UI settle
            }
        }

        if (!found) {
            addToReport("Loan account number not found after cycling through all cards.", Status.FAIL);
        }


            waitForElementPresence(lblOutStandngAmount, 20);
            OutStandingamountOnly = getTextFromElement(lblOutStandngAmount).replace("LKR", "").replace("-", "").trim();

            clickOnElement(lblLoanCard);
            addToReport("Click on the LKR account", Status.PASS);
            waitForElementPresence(lblDuration,PawnConstants.WAIT_EXTRA_LONG);
            clickOnElement(btnSettleLoan);
            addToReport("Click on the loan settlement button", Status.PASS);

            waitForElementPresence(btnSubmit, 20);
            if (isElementPresentBy(btnSubmit)) {
                addToReport("Loan Payments Page is Visible", Status.PASS, true);
            } else {
                addToReport("Loan Payments Page is not Visible", Status.FAIL);
                throw new RuntimeException("Error - Loan Payments Page is Visible");
            }

            selectFromDropdown(ddDebitedAccount, accountNumber, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
            addToReport("'" + accountNumber + "' number Selected from the dropDown", Status.PASS, false);

            sendKeysToElement(txtCustomAmountAmount, customAmount);
            addToReport("'" + customAmount + "'Enter as the custom amount", Status.PASS, true);

            clickOnElement(btnSubmit);
            addToReport("Click on the submit button", Status.PASS, false);

            waitForElementPresence(getSuccessfulMsg(successMsg), 20);
            //Validate the error message
            if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
                addToReport("'" + successMsg + "' message is present.", Status.PASS, true);
            } else {
                addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
                throw new RuntimeException("Error message validation is unsuccessful.");
            }

            amount = getTextFromElement(getSummaryValueByLabel("Amount")).replace("LKR", "").trim();
            charges = getTextFromElement(getSummaryValueByLabel("charges")).replace("LKR", "").trim();
            total = getTextFromElement(getSummaryValueByLabel("Total")).trim();

//validate that the enter amount displayed

            if (amount.equalsIgnoreCase(customAmount)) {
                addToReport("Amount is validated: " + amount, Status.PASS, true);
            } else {
                addToReport("Amount  mismatch. Expected: " + customAmount + ", Found: " + amount, Status.FAIL);
            }
            double amountt = Double.parseDouble(amount);
            double chargess = Double.parseDouble(charges);
            double totall = Double.parseDouble(total);

// Check if amount + charges equals total
            if (amountt + chargess == totall) {
                addToReport("Amount + Charges = Total (" + amount + " + " + charges + " = " + total + ")", Status.PASS, false);
            } else {
                addToReport("Amount + Charges ≠ Total (" + amount + " + " + charges + " ≠ " + total + ")", Status.FAIL);
            }

            OutStandingamountOnly = getTextFromElement(lblOutStandngAmount).replace("LKR", "").replace("-", "").replace(",", "").trim();
            double DoubleOutStandingamountOnly = Double.parseDouble(OutStandingamountOnly);
            double sheetAmount = Double.parseDouble(customAmount);
            ExpectedOutstandingAmountAfter = DoubleOutStandingamountOnly - sheetAmount;

            clearTheElement(rdAgreement);
            addToReport("Click on the agreement radio button", Status.PASS, false);


    }

        public void ValidatetheInstallmentDetails() {

            clearTheElement(btnSettlement);
            addToReport("Click on the settlement button", Status.PASS, true);

            //to refresh the card values
            clickOnElement(btnLeftArrow);
            clickOnElement(btnRightArrow);

            String formattedExpected = String.format("%,.2f", ExpectedOutstandingAmountAfter);

            if (OutStandingamountOnly.equalsIgnoreCase(formattedExpected)) {
                addToReport("Outstanding Amount matches expected value: " + OutStandingamountOnly, Status.PASS, true);
            } else {
                addToReport("Mismatch in Outstanding Amount. Expected: " + formattedExpected + ", Found: " + formattedExpected, Status.FAIL);
            }

            clickOnElement(lblLoanCard);
            addToReport("Click on the LKR account", Status.PASS, false);

            clickOnElement(btnPartialInstallments);
            addToReport("Click on the Partial Installments button", Status.PASS, false);

            waitForElementPresence(tblPartialInstallments, 20);
            addToReport("Partial Installments table visible", Status.PASS);


            boolean flag = true;

            // Get number of rows in the table
            List<WebElement> rows = driver.findElements(tblPartialInstallments);

            for (int i = 1; i <= rows.size(); i++) {
                String paidAmount = getTextFromElement(tblCellRecord(i, 1)).trim(); // Column 1: Paid Amount
                String transactionDate = getTextFromElement(tblCellRecord(i, 2)).trim(); // Column 2: Transaction Date

                // Validate Paid Amount - must be numeric after stripping LKR and commas
                String cleanAmount = paidAmount.replace("LKR", "").replace(",", "").trim();
                if (total.equalsIgnoreCase(cleanAmount)) {
                    addToReport("Row " + i + ": Valid Paid Amount: " + paidAmount, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Paid Amount: " + paidAmount, Status.FAIL);
                    flag = false;
                }

                // Validate Transaction Date - format: DD-MM-YYYY
                if (CommonUtils.containsValuesOnDate(transactionDate)) {
                    addToReport("Row " + i + ": Valid Transaction Date (Finacle): " + transactionDate, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Transaction Date: " + transactionDate, Status.FAIL);
                    flag = false;
                }
            }

            if (flag) {
                addToReport("All rows in Paid Transaction table validated successfully.", Status.PASS);
            } else {
                addToReport("Some rows in Paid Transaction table failed validation.", Status.FAIL);
            }

            clickOnElement(btnPaidInstallments);
            addToReport("Click on the Paid Installments button", Status.PASS, false);

            waitForElementPresence(tblPartialInstallments, 20);
            addToReport("Paid Installments table visible", Status.PASS);

            // Get number of rows in the table
            List<WebElement> rowss = driver.findElements(tblPartialInstallments);

            for (int i = 1; i <= rowss.size(); i++) {
                String dueDate = getTextFromElement(tblCellRecord(i, 1)).trim();
                String paidDate = getTextFromElement(tblCellRecord(i, 2)).trim();
                String capitalDemand = getTextFromElement(tblCellRecord(i, 3)).replace("LKR", "").replace(",", "").trim();
                String interestDemand = getTextFromElement(tblCellRecord(i, 4)).replace("LKR", "").replace(",", "").trim();
                String collectionAmount = getTextFromElement(tblCellRecord(i, 5)).replace("LKR", "").replace(",", "").trim();

                // Due Date validation
                if (CommonUtils.containsValuesOnDate(dueDate)) {
                    addToReport("Row " + i + ": Valid Due Date (Finacle):: " + dueDate, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Due Date: " + dueDate, Status.FAIL);
                    flag = false;
                }

                // Paid Date validation
                if (paidDate.equals("-") || CommonUtils.containsValuesOnDate(paidDate)) {
                    addToReport("Row " + i + ": Valid Paid Date (Finacle):: " + paidDate, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Paid Date: " + paidDate, Status.FAIL);
                    flag = false;
                }

                // Capital Demand validation
                if (CommonUtils.containsNumericCharactersWithNegativeValues(capitalDemand)) {
                    addToReport("Row " + i + ": Valid Capital Demand: " + capitalDemand, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Capital Demand: " + capitalDemand, Status.FAIL);
                    flag = false;
                }

                // Interest Demand validation
                if (CommonUtils.containsNumericCharactersWithNegativeValues(interestDemand)) {
                    addToReport("Row " + i + ": Valid Interest Demand: " + interestDemand, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Interest Demand: " + interestDemand, Status.FAIL);
                    flag = false;
                }

                // Collection Amount validation
                if (CommonUtils.containsNumericCharactersWithNegativeValues(collectionAmount)) {
                    addToReport("Row " + i + ": Valid Collection Amount: " + collectionAmount, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Collection Amount: " + collectionAmount, Status.FAIL);
                    flag = false;
                }
            }

            if (flag) {
                addToReport("All installment rows validated successfully.", Status.PASS);
            } else {
                addToReport("Some installment rows failed validation.", Status.FAIL);
            }


        }



}

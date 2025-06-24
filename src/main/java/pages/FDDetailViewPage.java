package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.FDDetailConstant;
import utils.constants.SaveAccountConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import static utils.Drivers.*;

public class FDDetailViewPage extends BasePage {

    public FDDetailViewPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblFixedDeposits = By.xpath("(//a[@class='SubMenu_subMenuItem___oYCo'])[2]");
    private static final By tblinterestAmount = By.xpath("//table[@class='min-w-full border-separate border-spacing-y-2 whitespace-nowrap']");
    private static final By lblACNumber = By.xpath("//span/ancestor::div[contains(@class,'flex justify-between')]/following::span[contains(@class,'flex flex-col')]");
    private static final By lblCurrencyAndAvailableBalance = By.xpath("//div[contains(text(),'Available')]/following-sibling::div/span[@class='text-black']");
    private static final By lblFDMaturityValue = By.xpath("//span[contains(text(),'Maturity Value')]/parent::div/span[1]");
    private static final By lblFDMaturityDate = By.xpath("//span[contains(text(),'Maturity Date')]/parent::div/span[1]");
    private static final By lblFDInterestRate = By.xpath("//span[contains(text(),'Interest Rate')]/parent::div/span[1]");
    private static final By imgWithdrawFD = By.xpath("//img[contains(@src, 'WithdrawFD') and contains(@class, 'object-contain')]");
    private static final By lblPrematureClosure= By.xpath("//span[contains(@class,'text-orange-500 text-center')]");
    private static final By lblFdDetails= By.xpath("//div[contains(@class, 'flex flex-col gap-4')]");
    private static final By ddCreditedAccount= By.xpath("//select[@id='accountfrom']");
    private static final By btnNext= By.xpath("//button[contains(@class, 'bg-green-500')]");
    private static final By withdrawConfirmationTitle = By.xpath("//span[@class='font-bold' and text()='" + SaveAccountConstants.FIXED_DEPOSIT_WITHDRAW + "']");



    public By getFDDetailByLabel(String label) {
        return By.xpath("//li[contains(., '" + label + "')]/span[@class='font-bold']");
    }
    public By getFDSectionValueByLabel(String label) {
        return By.xpath("//div[contains(@class, 'flex-col gap-4')]//div[contains(@class, 'justify-between') and .//div[text()='" + label + "']]//div[@class='font-bold']");
    }


    public void NavogatetoFDDetailViewPage () {

        waitForElementPresence(imgmyAccount);
        hoverOverElement(driver, imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS);

        waitForElementPresence(lblFixedDeposits);
        clickOnElement(lblFixedDeposits);
        addToReport("Clicked on the Fixed deposits tab ", Status.PASS);

        if (waitForElementPresence(tblinterestAmount, LONG_WAIT)) {
            addToReport("FD details amount table is visible.", Status.PASS, false);
        } else {
            addToReport("FD details amount table is not visible.", Status.FAIL);
        }

    }


    /**
     * Validate all the fd accounts at the dashboard
     *
     * @param currencyType
     * @param expectedMessage
     * @param fdAccountNumber
     */
    public void validateAllFDAccountsAtDashboard (String[] currencyType ,String expectedMessage, String fdAccountNumber) {

        waitForElementPresence(lblACNumber,LONG_WAIT);
        clickOnElement(lblACNumber);

        if (waitForElementPresence(tblinterestAmount, LONG_WAIT)) {
            addToReport("FD details amount table is visible.", Status.PASS, false);
        } else {
            addToReport("FD details amount table is not visible.", Status.FAIL);
        }

        boolean flag = true;

        //Validate deposit account details
        String AccountNo = CommonUtils.removeSpaceCharacters(getTextFromElement(lblACNumber));
        if (CommonUtils.containsNumericCharacters(AccountNo)) {
            addToReport("Successfully validated deposit account number : '" + AccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Deposit account number is not validated", Status.FAIL);
            flag = false;
        }

        //Validate currency and balance
        String[] CurrencyAndAmt = getTextFromElement(lblCurrencyAndAvailableBalance).split(" ");
        if (CurrencyAndAmt[0].equals("0.00")) {
            addToReport("Successfully validated currency and amount as 0.00", Status.PASS, false);
        } else if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
            addToReport("Successfully validated currency and amount " + CurrencyAndAmt[0] + CurrencyAndAmt[1], Status.PASS, false);
        } else {
            addToReport("Currency and amount is not validated", Status.FAIL);
            flag = false;
        }

        //Validate Maturity Amount
        String FDMaturityValue = getTextFromElement(lblFDMaturityValue);
        if (CommonUtils.containsNumericCharacters(FDMaturityValue)) {
            addToReport("Successfully validated maturity amount : '" + FDMaturityValue + "'", Status.PASS, false);
        } else {
            addToReport("Maturity amount : '" + FDMaturityValue + "' is not validated", Status.FAIL);
            flag = false;
        }

        //Validate Maturity Date
        String FDMaturityDate = getTextFromElement(lblFDMaturityDate).trim();
        String cleanFDMaturityDate = getTextFromElement(lblFDMaturityDate).replaceAll("\\D", "");
        if (CommonUtils.containsValuesOnDate(FDMaturityDate)) {
            addToReport("Successfully validated maturity date : '" + FDMaturityDate, Status.PASS, false);
        } else {
            addToReport("Maturity date : '" + FDMaturityDate + "' is not validated", Status.FAIL);
            flag = false;
        }

        //Validate Interest Rate
        String InterestRate = getTextFromElement(lblFDInterestRate);
        if (CommonUtils.containsAlphNumAndSpecialCharacters(InterestRate)) {
            addToReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS, false);
        } else {
            addToReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
            flag = false;
        }
        if (flag) {
            addToReport("Successfully validated fixed deposit : '" + AccountNo, Status.PASS, true);
        } else {
            flag = true;
        }



        String depositAmount = getTextFromElement(getFDDetailByLabel(FDDetailConstant.DEPOSIT_AMOUNT));
        String maturityAmount = getTextFromElement(getFDDetailByLabel(FDDetailConstant.MATURITY_AMOUNT));
        String maturityDate = getTextFromElement(getFDDetailByLabel(FDDetailConstant.MATURITY_DATE)).trim();
        String cleanMaturityDate = getTextFromElement(getFDDetailByLabel(FDDetailConstant.MATURITY_DATE)).replaceAll("\\D", "");
        String interestRate = getTextFromElement(getFDDetailByLabel(FDDetailConstant.INTEREST_RATE));
        String accountNumber = CommonUtils.removeSpaceCharacters(getTextFromElement(getFDDetailByLabel(FDDetailConstant.ACCOUNT_NUMBER)));



        if ((CurrencyAndAmt[0] + " " + CurrencyAndAmt[1]).equalsIgnoreCase(depositAmount)) {
            addToReport("Deposit Amount matches: " + depositAmount, Status.PASS, false);
        } else {
            addToReport("Deposit Amount mismatch. Expected: " + CurrencyAndAmt[0] + " " + CurrencyAndAmt[1] +
                    ", Actual: " + depositAmount, Status.FAIL);
        }

        // Normalize Maturity Amount
        String cleanExpectedAmount = FDMaturityValue.replace("LKR", "").replaceAll("[^\\d.,]", "").trim();
        String cleanActualAmount = maturityAmount.replace("LKR", "").replaceAll("[^\\d.,]", "").trim();
        if (cleanExpectedAmount.equalsIgnoreCase(cleanActualAmount)) {
            addToReport("Maturity Amount matches: " + cleanActualAmount, Status.PASS, false);
        } else {
            addToReport("Maturity Amount mismatch. Expected: " + cleanExpectedAmount + ", Actual: " + cleanActualAmount, Status.FAIL);
        }

// Normalize Maturity Date

        if (cleanFDMaturityDate.equalsIgnoreCase(cleanMaturityDate)) {
            addToReport("Maturity Date matches: " + cleanMaturityDate, Status.PASS, false);
        } else {
            addToReport("Maturity Date mismatch. Expected: " + cleanFDMaturityDate + ", Actual: " + cleanMaturityDate, Status.FAIL);
        }


        if (InterestRate.equalsIgnoreCase(interestRate)) {
            addToReport("Interest Rate matches: " + interestRate, Status.PASS, false);
        } else {
            addToReport("Interest Rate mismatch. Expected: " + InterestRate + ", Actual: " + interestRate, Status.FAIL);
        }

        if (AccountNo.equalsIgnoreCase(accountNumber)) {
            addToReport("Account Number matches: " + accountNumber, Status.PASS, false);
        } else {
            addToReport("Account Number mismatch. Expected: " + AccountNo + ", Actual: " + accountNumber, Status.FAIL);
        }

//validate the values that only available under the account summary section

        //Lien Amount
        String[] lienAmt = getTextFromElement(getFDDetailByLabel(FDDetailConstant.LIEN_AMOUNT)).split(" ");
        if (lienAmt[0].equals("0.00")) {
            addToReport("Successfully validated Lien Amount as 0.00", Status.PASS, false);
        } else if (Arrays.asList(currencyType).contains(lienAmt[0]) &&
                CommonUtils.containsNumericCharacters(lienAmt[1])) {
            addToReport("Successfully validated Lien Amount " + lienAmt[0] + lienAmt[1], Status.PASS, false);
        } else {
            addToReport("Lien Amount is not validated", Status.FAIL);
            flag = false;
        }

        //Account open date
        String fdOpenDate =  getTextFromElement(getFDDetailByLabel(FDDetailConstant.ACCOUNT_OPEN_DATE));
        if (CommonUtils.containsValuesOnDate(fdOpenDate)) {
            addToReport("Successfully validated Open date : '" + fdOpenDate, Status.PASS, false);
        } else {
            addToReport("Open date : '" + fdOpenDate + "' is not validated", Status.FAIL);
            flag = false;
        }

        //deposit period
        String depositPeriod = getTextFromElement(getFDDetailByLabel(FDDetailConstant.DEPOSIT_PERIOD_IN_MONTHS));
        if (CommonUtils.containsNumericCharacters(depositPeriod)) {
            addToReport("Successfully validated deposit period : '" + depositPeriod, Status.PASS, false);
        } else {
            addToReport("deposit period : '" + depositPeriod + "' is not validated", Status.FAIL);
            flag = false;
        }




        clickOnElement(imgWithdrawFD);
        addToReport("Successfully clicked on the FD withdraw button", Status.PASS, false);

        waitForElementPresence(lblFdDetails,20);
        addToReport("Successfully visible the detail view", Status.PASS, false);




        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Update format as per your UI
        Date currentDate = new Date();
        String currentDateFormatted = sdf.format(currentDate);

// Log current system date
        addToReport(" Current system date: " + currentDateFormatted, Status.INFO, false);

        try {
            if (CommonUtils.containsValuesOnDate(FDMaturityDate)) {
                addToReport(" Successfully validated maturity date: '" + FDMaturityDate + "'", Status.PASS, false);

                Date maturityDatecompair = sdf.parse(FDMaturityDate);

                // Compare with current date
                if (maturityDatecompair.after(currentDate)) {
                    addToReport(" Maturity date is in the future. Possible premature closure.", Status.INFO, false);

                    // Validate Premature Closure message
                    if (waitForElementPresence(lblPrematureClosure, MODERATE_WAIT)) {
                        String actualMessage = getTextFromElement(lblPrematureClosure).trim();

                        if (actualMessage.equalsIgnoreCase(expectedMessage)) {
                            addToReport(" Premature closure message is correct: '" + actualMessage + "'", Status.PASS);
                        } else {
                            addToReport(" Premature closure message mismatch. Expected: '" + expectedMessage + "', Found: '" + actualMessage + "'", Status.FAIL);
                        }
                    } else {
                        addToReport(" Premature closure message element is not visible.", Status.FAIL);
                        flag = false;
                    }

                }

            } else {
                addToReport(" Maturity date: '" + FDMaturityDate + "' is not in a valid format.", Status.FAIL);
                flag = false;
            }

        } catch (ParseException e) {
            addToReport(" Error parsing maturity date: " + e.getMessage(), Status.FAIL);
            flag = false;
        }



        clickOnElement(ddCreditedAccount);
        selectFromDropdown(ddCreditedAccount, fdAccountNumber, "value");
        addToReport("Selected account number '" + fdAccountNumber + "' from dropdown.", Status.PASS, true);

        String amountValue = getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.AMOUNT));
        String interestEarnedValue = getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_EARNED));
        String interestRateValue = getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_RATE));
        String totalValue = getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.TOTAL));

        clickOnElement(btnNext);
        addToReport("Successfully clicked on the next button", Status.PASS);
        validateMessage(withdrawConfirmationTitle,SaveAccountConstants.FIXED_DEPOSIT_WITHDRAW);

        if (amountValue.equalsIgnoreCase(getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.AMOUNT)))) {
            addToReport("Amount matches: " + amountValue, Status.PASS, false);
        } else {
            addToReport("Amount mismatch. Expected: " + amountValue + ", Actual: " + getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.AMOUNT)), Status.FAIL);
        }
        if (interestEarnedValue.equalsIgnoreCase(getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_EARNED)))) {
            addToReport("Interests earned matches: " + interestEarnedValue, Status.PASS, false);
        }
        else {
            addToReport("Interests earned mismatch. Expected: " + interestEarnedValue + ", Actual: " + getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_EARNED)), Status.FAIL);
        }
        if (interestRateValue.equalsIgnoreCase(getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_RATE)))) {
            addToReport("Interests Rate matches: " + interestRateValue, Status.PASS, false);
        }
        else {
            addToReport("Interests Rate mismatch. Expected: " + interestRateValue + ", Actual: " + getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.INTERESTS_RATE)), Status.FAIL);
        }
        if (totalValue.equalsIgnoreCase(getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.TOTAL)))) {
            addToReport("Total matches: " + totalValue, Status.PASS, false);
        }
        else {
            addToReport("Total mismatch. Expected: " + totalValue + ", Actual: " + getTextFromElement(getFDSectionValueByLabel(FDDetailConstant.TOTAL)), Status.FAIL);
        }
        if (flag) {
            addToReport("Successfully validated fixed deposit : '" + AccountNo, Status.PASS, true);
        }
        else {
            flag = true;
        }



    }

    private void validateMessage(By locator, String expectedMessage) {
        if (waitForElementPresence(locator, MODERATE_WAIT)) {
            String actualMsg = getTextFromElement(locator);
            if (actualMsg != null && actualMsg.trim().equalsIgnoreCase(expectedMessage)) {
                addToReport("Validation successful: " + actualMsg.trim(), Status.PASS);
            } else {
                addToReport("Message mismatch. Expected: " + expectedMessage +
                        ", Actual: " + (actualMsg != null ? actualMsg.trim() : "null"), Status.FAIL);
            }
        } else {
            addToReport("Message not found for validation. Expected: " + expectedMessage, Status.FAIL);
        }
    }


// }


}
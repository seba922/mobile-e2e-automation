package org.example.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final SelenideElement standardUserButton = $x("//android.widget.TextView[@text='standard_user']");
    private final SelenideElement loginButton = $x("//android.widget.TextView[@text='LOGIN']");

    public ProductListPage loginAsDefaultUser() {
        click(standardUserButton);
        click(loginButton);

        return new ProductListPage();
    }
}

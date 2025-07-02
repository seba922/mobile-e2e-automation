package org.example;

import org.example.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppiumExampleTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();

    @DisplayName("Filter products by name")
    @Test
    public void tc_007() {
        List<String> listOfAllNames = loginPage.loginAsDefaultUser()
                .clickFilterButton()
                .clickAlphabetically()
                .getAllNames();

        assertThat(listOfAllNames)
                .as("Item names are not sorted alphabetically")
                .isSorted();
        assertThat(listOfAllNames.size())
                .as("There is only one item on the page, cannot check sorting function")
                .isGreaterThan(1);
    }
}

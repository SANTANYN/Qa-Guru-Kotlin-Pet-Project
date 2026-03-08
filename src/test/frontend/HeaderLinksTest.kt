package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Epic("Frontend Tests")
@Feature("Header Navigation")
@Owner("mikhail")
class HeaderLinksTest : BaseUiTest() {

    private val homePage = HomeViewPage()

    @DisplayName("Check header links presence")
    @ParameterizedTest(name = "Link: {0}")
    @Story("Navigation links")
    @ValueSource(strings = ["Products", "Orders", "Contact", "Cart", "Join"])
    fun `should have link in header`(linkName: String) {
        homePage
            .openPage()
            .header().clickLink(linkName)
    }
}

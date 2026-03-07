package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HeaderLinksTest : BaseUiTest() {

    private val homePage = HomeViewPage()

    @DisplayName("Check header links presence")
    @ParameterizedTest(name = "Link: {0}")
    @ValueSource(strings = ["Products", "Orders", "Contact", "Cart", "Join"])
    fun `should have link in header`(linkName: String) {
        homePage.header().clickLink(linkName)
    }
}

package frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.SelenideElement

class Wrappers {
    companion object {
        fun byDataTestGroup(target: String) = Selectors.by("data-test-group", target)
        fun byDataTestId(target: String) = Selectors.by("data-test-id", target)

        fun SelenideElement.shouldBeVisible(): Boolean {
            this.shouldBe(visible)
            return this.isDisplayed
        }
    }
}

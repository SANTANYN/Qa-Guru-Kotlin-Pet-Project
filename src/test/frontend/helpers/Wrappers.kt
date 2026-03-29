package frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.SelenideElement

class Wrappers {
    companion object {
        /** Селектор по `data-test-group`: однотипные элементы списка (карточки, строки корзины). */
        fun byDataTestGroup(target: String) = Selectors.by("data-test-group", target)

        /** Селектор по `data-test-id`: уникальный якорь элемента, не зависящий от CSS-классов. */
        fun byDataTestId(target: String) = Selectors.by("data-test-id", target)

        fun SelenideElement.shouldBeVisible(): Boolean {
            this.shouldBe(visible)
            return this.isDisplayed
        }
    }
}

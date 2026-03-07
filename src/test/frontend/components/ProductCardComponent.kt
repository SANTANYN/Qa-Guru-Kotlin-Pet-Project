package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.SelenideElement

/**
 * Важный принцип: мы принимаем в конструкторе корневой элемент карточки товара.
 * Все внутренние элементы ищем только внутри этого корневого элемента, 
 * обеспечивая инкапсуляцию.
 */
class ProductCardComponent(private val root: SelenideElement) {

    private val name = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-name"))
    private val description = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-description"))
    private val price = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-price"))
    private val incrementBtn = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-increment"))
    private val decrementBtn = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-decrement"))
    private val qty = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-qty"))

    fun getNameText(): String = name.text
    fun getPriceText(): String = price.text
    fun getDescriptionText(): String = description.text
    fun getQuantityText(): String = qty.text

    fun checkName(expectedName: String) {
        name.shouldHave(text(expectedName))
    }

    fun checkPrice(expectedPrice: String) {
        price.shouldHave(text(expectedPrice))
    }

    fun clickIncrement() {
        incrementBtn.click()
    }

    fun clickDecrement() {
        decrementBtn.click()
    }
}

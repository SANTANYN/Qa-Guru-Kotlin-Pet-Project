package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import frontend.extensions.findByOrFail
import io.qameta.allure.Step

/**
 * Контейнер для карточек товаров.
 * Возвращает дочерние компоненты-карточки (ProductCardComponent)
 */
class ProductsContainerComponent {

    private val container = `$`(".products-container")
    private val cardsCollection = container.`$$`(frontend.helpers.Wrappers.byDataTestGroup("product-card"))

    fun getCardsCollection() = cardsCollection

    fun getAllCards(): List<ProductCardComponent> {
        return cardsCollection.map { ProductCardComponent(it) }
    }

    @Step("Найти карточку товара по имени: {productName}")
    fun getCardByName(productName: String): ProductCardComponent {
        val element = cardsCollection.findByOrFail(
            text(productName),
            "Карточка товара с именем '$productName' не найдена в контейнере!"
        )
        return ProductCardComponent(element)
    }

    @Step("Найти карточку товара по индексу: {index}")
    fun getCardByIndex(index: Int): ProductCardComponent {
        return ProductCardComponent(cardsCollection[index])
    }
}

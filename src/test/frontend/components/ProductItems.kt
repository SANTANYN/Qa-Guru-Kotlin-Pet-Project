import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

/**
 * Data-класс для представления состояния одного товара.
 * Позволяет удобно обращаться к данным товара (имя, цена, кол-во) как к свойствам объекта.
 */
data class ProductItem(
    val image: SelenideElement,
    val name: String,
    val description: String,
    val price: Float,
    val btnIncrement: SelenideElement,
    val quantity: Int,
    val btnDecrement: SelenideElement
)

/**
 * Компонент-коллекция для работы со списком товаров через Data-классы.
 */
class ProductItems {

    private val listProducts = `$$`(byDataTestGroup("product-card"))

    @Step("Получить список товаров как коллекцию ProductItem")
    fun getItems(): List<ProductItem> {
        // Добавляем ожидание, чтобы коллекция успела загрузиться
        listProducts.shouldHave(CollectionCondition.sizeGreaterThan(0))
        
        return listProducts.map { card ->
            ProductItem(
                image = card.`$`(byDataTestGroup("product-card-image")),
                name = card.`$`(byDataTestGroup("product-card-name")).text(),
                description = card.`$`(byDataTestGroup("product-card-description")).text(),
                price = card.`$`(byDataTestGroup("product-card-price")).text()
                    .filter { it.isDigit() || it == '.' }.toFloat(),
                btnIncrement = card.`$`(byDataTestGroup("product-card-increment")),
                quantity = card.`$`(byDataTestGroup("product-card-qty")).text().toInt(),
                btnDecrement = card.`$`(byDataTestGroup("product-card-decrement"))
            )
        }
    }
}

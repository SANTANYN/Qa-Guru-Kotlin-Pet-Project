package frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.open
import frontend.components.HeaderComponent
import frontend.components.MainImageComponent
import frontend.components.ProductsContainerComponent
import frontend.components.PopularProductsTitleComponent
import frontend.components.PopularItem
import frontend.components.PopularItems
import io.qameta.allure.Step

class MainPage {

    private val header = HeaderComponent()
    private val mainImage = MainImageComponent()
    private val productsContainer = ProductsContainerComponent()
    private val popularProductsTitle = PopularProductsTitleComponent()
    private val popularItems = PopularItems()

    @Step("Открыть главную страницу")
    fun openPage(): MainPage {
        open("/")
        return this
    }

    @Step("Проверить заголовок популярных товаров: {expectedText}")
    fun checkPopularProductsTitleText(expectedText: String): MainPage {
        popularProductsTitle.checkText(expectedText)
        return this
    }

    @Step("Проверить количество товаров: {expectedSize}")
    fun checkProductsCount(expectedSize: Int): MainPage {
        productsContainer.getCardsCollection().shouldHave(CollectionCondition.size(expectedSize))
        return this
    }

    @Step("Проверить видимость главного изображения")
    fun checkMainImageVisible(): MainPage {
        mainImage.shouldBeVisible()
        return this
    }

    @Step("Проверить текст на главном изображении: {expectedText}")
    fun checkMainImageText(expectedText: String): MainPage {
        mainImage.shouldHaveText(expectedText)
        return this
    }

    fun header(): HeaderComponent = header

    fun products(): ProductsContainerComponent = productsContainer

    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<PopularItem> = popularItems.getItems()

    @Step("Нажать на ссылку Products в хедере")
    fun clickProductsLink(): MainPage {
        header.clickLink("Products")
        return this
    }
}

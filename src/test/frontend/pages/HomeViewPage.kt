package frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.open
import frontend.components.HeaderComponent
import frontend.components.MainImageComponent
import frontend.components.list.ProductsContainerComponent
import frontend.components.PopularProductsTitleComponent
import frontend.components.list.ProductItemsList
import io.qameta.allure.Step

class HomeViewPage {

    private val header = HeaderComponent()
    private val mainImage = MainImageComponent()
    private val productsContainer = ProductsContainerComponent()
    private val popularProductsTitle = PopularProductsTitleComponent()
    private val popularItems = ProductItemsList()

    @Step("Открыть главную страницу")
    fun openPage(): HomeViewPage {
        open("/")
        return this
    }

    @Step("Проверить заголовок популярных товаров: {expectedText}")
    fun checkPopularProductsTitleText(expectedText: String): HomeViewPage {
        popularProductsTitle.checkText(expectedText)
        return this
    }

    @Step("Проверить количество товаров: {expectedSize}")
    fun checkProductsCount(expectedSize: Int): HomeViewPage {
        productsContainer.getCardsCollection().shouldHave(CollectionCondition.size(expectedSize))
        return this
    }

    @Step("Проверить видимость главного изображения")
    fun checkMainImageVisible(): HomeViewPage {
        mainImage.shouldBeVisible()
        return this
    }

    @Step("Проверить текст на главном изображении: {expectedText}")
    fun checkMainImageText(expectedText: String): HomeViewPage {
        mainImage.shouldHaveText(expectedText)
        return this
    }

    fun header(): HeaderComponent = header
    
    fun products(): ProductsContainerComponent = productsContainer

    fun getPopularItems(): ProductItemsList = popularItems

    @Step("Нажать на ссылку Products в хедере")
    fun clickProductsLink(): HomeViewPage {
        header.clickLink("Products")
        return this
    }
}

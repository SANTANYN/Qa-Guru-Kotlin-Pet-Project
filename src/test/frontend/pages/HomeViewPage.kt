package frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.open
import frontend.components.HeaderComponent
import frontend.components.MainImageComponent
import frontend.components.list.ProductsContainerComponent
import frontend.components.PopularProductsTitleComponent
import frontend.components.list.ProductItemsList
import frontend.components.popup.CreateUserPopup
import frontend.components.popup.LoginPopup
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

    @Step("Получить заголовок популярных товаров")
    fun getPopularProductsTitleText(): String = popularProductsTitle.getText()

    @Step("Получить количество товаров")
    fun getProductsCount(): Int = productsContainer.getCardsCollection().size()

    @Step("Получить текст на главном изображении")
    fun getMainImageText(): String = mainImage.getElement().text() ?: ""

    fun getMainImage() = mainImage

    fun header(): HeaderComponent = header

    @Step("Нажать Join в шапке")
    fun clickJoin(): CreateUserPopup = header.clickJoin()

    /**
     * Открывает диалог логина.
     *
     * В текущем UI кнопка **Join** сначала открывает регистрацию; вход — по ссылке «your account».
     * Этот метод прячет эту цепочку, чтобы в тестах был сценарий как `openLoginForm().authPopup()...`.
     */
    @Step("Открыть форму логина")
    fun openLoginForm(): HomeViewPage {
        header.clickJoin()
        CreateUserPopup().clickLoginLink()
        return this
    }

    @Step("Попап авторизации")
    fun authPopup(): LoginPopup = LoginPopup()

    fun products(): ProductsContainerComponent = productsContainer

    fun getPopularItems(): ProductItemsList = popularItems
    
    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<frontend.components.list.ProductItem> = popularItems.getItems()

    @Step("Нажать на ссылку Products в хедере")
    fun clickProductsLink(): HomeViewPage {
        header.clickLink("Products")
        return this
    }
}

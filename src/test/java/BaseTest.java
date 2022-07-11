import com.codeborne.selenide.Configuration;
import data.TestConsts;
import data.UserResponse;
import org.junit.After;
import org.junit.Before;
import page.LoginPage;
import page.MainPage;
import client.UserClient;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    MainPage mainPage;
    UserClient userClient = new UserClient();
    protected UserResponse user;

    @Before
    public void setUp() {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        mainPage = open(TestConsts.URL_MAIN_PAGE,
                MainPage.class);
    }

    @After
    public void tearDown() {
        if(user != null && user.getAccessToken() != null) {
            userClient.deleteUser(user.getAccessToken());
        }
        user = null;
    }
}
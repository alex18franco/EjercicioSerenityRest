package co.com.reto2021.wolox.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/usuarios.feature"},
        glue = {"co.com.reto2021.wolox.stepdefinitions"},
        snippets = SnippetType.CAMELCASE
)
public class UsuarioRunner {
}

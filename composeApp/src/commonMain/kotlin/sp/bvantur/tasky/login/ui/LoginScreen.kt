package sp.bvantur.tasky.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import sp.bvantur.tasky.core.ui.components.ConfirmationButton
import sp.bvantur.tasky.core.ui.components.EmailTextField
import sp.bvantur.tasky.core.ui.components.HyperlinkText
import sp.bvantur.tasky.core.ui.components.PasswordTextField
import sp.bvantur.tasky.core.ui.components.TitleText
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.email_address
import tasky.composeapp.generated.resources.login
import tasky.composeapp.generated.resources.no_account_sign_up
import tasky.composeapp.generated.resources.password
import tasky.composeapp.generated.resources.sign_up
import tasky.composeapp.generated.resources.welcome_back

@Composable
fun LoginRoute() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(
            text = stringResource(Res.string.welcome_back),
            modifier = Modifier.padding(top = 50.dp)
                .padding(horizontal = 16.dp)
        )

        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(top = 42.dp),
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailTextField(
                    value = "", // TODO
                    onValueChange = {
                        // TODO
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 50.dp),
                    placeholder = stringResource(Res.string.email_address)
                )

                PasswordTextField(
                    value = "", // TODO
                    onValueChange = {
                        // TODO
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 15.dp),
                    placeholder = stringResource(Res.string.password)
                )

                ConfirmationButton(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 25.dp)
                        .defaultMinSize(minHeight = 56.dp),
                    text = stringResource(Res.string.login),
                    onClick = {
                        // TODO
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                HyperlinkText(
                    allText = stringResource(Res.string.no_account_sign_up),
                    hyperlinkText = stringResource(Res.string.sign_up),
                    modifier = Modifier.padding(bottom = 40.dp),
                    onClick = {
                        // TODO
                    }
                )
            }
        }
    }
}

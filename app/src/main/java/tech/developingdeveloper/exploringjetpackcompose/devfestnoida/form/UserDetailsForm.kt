package tech.developingdeveloper.exploringjetpackcompose.devfestnoida.form

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun UserDetailsForm(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        //region State Declarations
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        //endregion

        Title(
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        NameInput(name, onNameChange = { phoneNumber = it })

        Spacer(modifier = Modifier.height(8.dp))

        EmailInput(email = email, onEmailChange = { email = it })

        Spacer(Modifier.height(8.dp))

        PhoneNumberInput(phoneNumber = phoneNumber, onPhoneNumberChange = { phoneNumber = it })

        Spacer(Modifier.height(8.dp))

        DateOfBirthInput2(dob = dob, onDobChange = { dob = it })

        Spacer(modifier = Modifier.height(12.dp))

        ActionButtons()
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        text = "Register",
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier,
    )
}

@Composable
private fun NameInput(name: String, onNameChange: (String) -> Unit) {
    Input(
        label = "Name",
        value = name,
        onValueChange = onNameChange,
        leadingIcon = Icons.Default.Person
    )
}

@Composable
private fun EmailInput(email: String, onEmailChange: (String) -> Unit) {
    Input(
        label = "Email",
        value = email,
        onValueChange = onEmailChange,
        leadingIcon = Icons.Default.Email,
    )
}

@Composable
private fun PhoneNumberInput(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
) {
    Input(
        label = "Phone Number",
        value = phoneNumber,
        onValueChange = onPhoneNumberChange,
        leadingIcon = Icons.Default.Phone,
    )
}

@Composable
private fun DateOfBirthInput(
    dob: String,
    onDobChange: (String) -> Unit,
) {
    Input(
        label = "Date of Birth",
        value = dob,
        onValueChange = onDobChange,
        leadingIcon = Icons.Default.CalendarMonth
    )
}

@Composable
private fun DateOfBirthInput2(
    dob: String,
    onDobChange: (String) -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceState by interactionSource.collectIsPressedAsState()

    LaunchedEffect(key1 = interactionSourceState) {
        if (interactionSourceState && !showDatePicker) {
            showDatePicker = true
        }
    }

    if(showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    onDobChange(datePickerState.selectedDateMillis?.toDateString() ?: "")
                    showDatePicker = false
                }) {
                    Text("Select")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Text(
        text = "Date of Birth",
        style = MaterialTheme.typography.labelMedium,
        color = Color.Black.copy(alpha = 0.5f)
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = dob,
        onValueChange = onDobChange,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { showDatePicker = true }) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null
                )
            }
        },
        readOnly = true,
        interactionSource = interactionSource,
    )
}

@Composable
private fun DateOfBirthInput(
    showDatePicker: Boolean,
    onShowDatePickerChange: (Boolean) -> Unit,
    interactionSource: MutableInteractionSource,
    datePickerState: DatePickerState = rememberDatePickerState(),
) {

    val interactionSourceState by interactionSource.collectIsPressedAsState()

    LaunchedEffect(key1 = interactionSourceState) {
        if (interactionSourceState && !showDatePicker) {
            onShowDatePickerChange(false)
        }
    }

    if(showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { onShowDatePickerChange(false) },
            confirmButton = {
                Button(onClick = { onShowDatePickerChange(false) }) {
                    Text("Select")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Text(
        text = "Date of Birth",
        style = MaterialTheme.typography.labelMedium,
        color = Color.Black.copy(alpha = 0.5f)
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = datePickerState.selectedDateMillis?.toDateString() ?: "",
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { onShowDatePickerChange(true) }) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null
                )
            }
        },
        readOnly = true,
        interactionSource = interactionSource,
    )
}

private fun Long.toDateString(): String {
    return SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault()).format(Date(this))
}

@Composable
private fun Input(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector? = null,
) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = Color.Black.copy(alpha = 0.5f)
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                )
            }
        } else null,
    )
}

@Composable
private fun ActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CancelButton(modifier = Modifier.weight(0.4f))

        Spacer(modifier = Modifier.width(8.dp))

        RegisterButton(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun CancelButton(modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = {},
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(),
    ) {
        Text("Cancel")
    }
}

@Composable
private fun RegisterButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(),
    ) {
        Text("Register")
    }
}

const val USER_DETAILS_FORM_UNLABELLED_ROUTE = "user_details_form_unlabelled"

@Composable
fun UserDetailsFormUnlabelled(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        //region State Declarations
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        //endregion

        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Label",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it.trim()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Label",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black.copy(alpha = 0.5f)
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it.trim()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Label",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black.copy(alpha = 0.5f)
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it.trim()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Label",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black.copy(alpha = 0.5f)
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it.trim()
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(0.4f),
                colors = ButtonDefaults.outlinedButtonColors(),
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Register")
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun UserDetailsFormPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            UserDetailsForm()
        }
    }
}
package com.example.hooks2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.material3.Text
import com.example.hooks2.ui.theme.Hooks2Theme

class MainActivity : ComponentActivity() {

    private var randomNumber by mutableStateOf(0)

    // Clave para el Bundle
    companion object {
        private const val RANDOM_NUMBER = "RANDOM_NUMBER"
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(0, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Restaurar el estado si savedInstanceState no es nulo
        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(RANDOM_NUMBER, 0)
        }

        enableEdgeToEdge()
        setContent {
            Hooks2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                randomNumber = generateRandomNumber()
                            }
                        ) {
                            Text(stringResource(id = R.string.generate_random_number), fontSize = 18.sp)
                        }
                        Text(
                            stringResource(id = R.string.random_number_message, randomNumber),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }

    // Guardar el estado antes de la destrucción de la actividad
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER, randomNumber)
    }
}
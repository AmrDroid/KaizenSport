package com.aelsayed.kaizen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aelsayed.kaizen.presentation.screen.HomeScreen
import com.aelsayed.kaizen.presentation.ui.theme.KaizenSportTheme
import com.aelsayed.kaizen.presentation.viewmodel.KaizenSportViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: KaizenSportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                viewModel.state.collect {

                    setContent {
                        KaizenSportTheme {
                            Column {
                                HomeScreen(viewModel)
                            }
                        }
                    }

                }


            }


        }
    }
}

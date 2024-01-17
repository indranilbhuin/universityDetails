package com.example.universitydetails

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.universitydetails.ui.theme.UniversityDetailsTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityScreen(navController: NavHostController, viewModel: UniversityViewModel) {
    val universitiesState: State<List<UniversityItem>> =
        viewModel.universityData.observeAsState(emptyList())

    val universities = universitiesState.value
    LaunchedEffect(Unit) {
        viewModel.fetchUniversities()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "All Universities",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }, navigationIcon = {
                Row() {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }

            }, modifier = Modifier.padding(16.dp, top = 0.dp)
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 50.dp),
        ) {
            items(universities) { university ->
                UniversityCard(university) {
                    navController.navigate("universityDetails/${university.name}")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityCard(university: UniversityItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.5.dp, Color(0xFF353535)),
    ) {
        Column(modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp)) {
            Text(
                text = university.name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Country: ${university.country}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (university.state_province != null) {
                Text(
                    text = "State/Province: ${university.state_province}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Text(
                text = "Domains: ${university.domains.joinToString(", ")}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            val uriHandler = LocalUriHandler.current

            Text(text = "Web Pages: ${university.web_pages.joinToString(", ")}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF4285F4),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable {
                        uriHandler.openUri(university.web_pages.joinToString(", "))
                    })

        }
    }
}

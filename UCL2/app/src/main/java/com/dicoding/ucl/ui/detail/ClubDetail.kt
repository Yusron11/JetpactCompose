package com.dicoding.ucl.ui.clubdetail


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.ucl.MainViewModel
import com.dicoding.ucl.data.ClubRepository
import com.dicoding.ucl.ui.theme.UCLTheme
import coil.compose.AsyncImage
import com.dicoding.ucl.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubDetail(
    modifier: Modifier = Modifier,
    clubId: String,
    viewModel: MainViewModel,
    navController: NavController
) {
    val club = viewModel.getClubById(clubId)

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detail))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.kembali))
                    }
                },
                actions = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = club.photoUrl,
                    contentDescription = stringResource(R.string.photo),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = club.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Liga: ${club.liga}",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = club.detail,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClubDetailPreview() {
    UCLTheme {
        ClubDetail(clubId = "1", viewModel = MainViewModel(repository = ClubRepository()), navController = rememberNavController())
    }
}

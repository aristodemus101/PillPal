package com.example.pillpal.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun AddMedicineScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp), // Add padding from the top
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Add height to the TabRow
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                modifier = Modifier.weight(1f)
            ) {
                Text("Upload a Prescription", fontSize = 14.sp)
            }
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                modifier = Modifier.weight(1f)
            ) {
                Text("Manually Enter Data", fontSize = 14.sp)
            }
        }

        when (selectedTab) {
            0 -> UploadPrescriptionSection()
            1 -> ManualEntrySection(navController)
        }
    }
}
@Composable
fun UploadPrescriptionSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Upload a Prescription")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle image upload and recognition */ }) {
            Text("Upload Image")
        }
    }
}

@Composable
fun ManualEntrySection(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var dosage by remember { mutableStateOf("") }
    var timeOfDay by remember { mutableStateOf("") }
    var firstStarted by remember { mutableStateOf(LocalDate.now()) }
    var doctor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Medicine Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = dosage,
            onValueChange = { dosage = it },
            label = { Text("Dosage") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = timeOfDay,
            onValueChange = { timeOfDay = it },
            label = { Text("Time of Day") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = doctor,
            onValueChange = { doctor = it },
            label = { Text("Doctor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Save medicine and navigate back */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
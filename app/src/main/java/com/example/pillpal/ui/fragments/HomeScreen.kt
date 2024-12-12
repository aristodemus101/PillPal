package com.example.pillpal.ui.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pillpal.data.models.Medicine
import com.example.pillpal.ui.navigation.Screen
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val medicines = remember {
        mutableStateListOf(
            Medicine(name = "Medicine 1", dosage = "Dosage: 1 pill", date = LocalDate.now()),
            Medicine(name = "Medicine 2", dosage = "Dosage: 2 pills", date = LocalDate.now().plusDays(1)),
            Medicine(name = "Medicine 3", dosage = "Dosage: 3 pills", date = LocalDate.now().plusDays(2)),
            Medicine(name = "Medicine 4", dosage = "Dosage: 4 pills", date = LocalDate.now())
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "PillPal", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle profile click */ }) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.fillMaxWidth()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.ADD_PILL.name) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Medicine",
                    Modifier.background(Color.White, shape = RoundedCornerShape(50)),
                    tint = Color(0xFFBBDEFB)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CalendarView(selectedDate) { date ->
                selectedDate = date
            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(medicines.filter { it.date == selectedDate }) { medicine ->
                    var checked by remember { mutableStateOf(medicine.taken) }
                    MedicineCard(medicine, checked) { isChecked ->
                        checked = isChecked
                        val updatedMedicine = medicine.copy(taken = isChecked)
                        medicines.remove(medicine)
                        medicines.add(updatedMedicine)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarView(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val currentMonth = YearMonth.now()
    val today = LocalDate.now()
    val daysInMonth = currentMonth.lengthOfMonth()
    val days = (1..daysInMonth).map { day ->
        LocalDate.of(currentMonth.year, currentMonth.month, day)
    }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.scrollToItem(today.dayOfMonth - 1)
    }

    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(state = listState) {
            items(days) { date ->
                DayColumn(date, date == today, date == selectedDate) {
                    onDateSelected(date)
                }
            }
        }
    }
}

@Composable
fun DayColumn(date: LocalDate, isToday: Boolean, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(
                when {
                    isToday -> Color(0xFFBBDEFB) // Light Blue
                    isSelected -> Color(0xFFFFCDD2) // Light Red
                    else -> Color.Transparent
                },
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = date.dayOfMonth.toString(), fontSize = 16.sp)
    }
}

@Composable
fun MedicineCard(medicine: Medicine, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = if (checked) Color(0xFFBBDEFB) else Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Medicine",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medicine.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = medicine.dosage, fontSize = 14.sp)
            }
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
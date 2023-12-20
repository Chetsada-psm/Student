package com.example.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.student.ui.theme.StudentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentTheme {
                // A surface container using the 'background' color from the theme
                StudentApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentApp(){
    var viewModel = StudentViewModel()
    var data = remember { mutableStateListOf<String>() }
    var isShowDialog = remember {
        mutableStateOf(false)
    }
    if(isShowDialog.value){
        InputDialog(
            onCancel = { isShowDialog.value = false },
            onAddButtonClick = { newItemName ->
                data.add(newItemName)
                isShowDialog.value = false
            })
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("StudentApp")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Magenta
                ))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isShowDialog.value = true },
                containerColor = Color.Magenta) {
                Icon(Icons.Filled.Add,
                    "Add new Items",
                    tint = Color.White)
            }
        }
    ){
        LazyColumn(
            modifier = Modifier.padding(it)){
            items(viewModel.data){
                    item->Text(item.StudentName)

            }
        }
    }
}

@Composable
private fun InputDialog(
    onCancel: () -> Unit,
    onAddButtonClick: (String) -> Unit
) {
    Dialog(
        onDismissRequest = onCancel,
    ){
        var StudentID by remember {
            mutableStateOf("")
        }
        var StudentName by remember {
            mutableStateOf("")
        }
        Card(
            shape = RoundedCornerShape(8.dp)){
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                TextField(
                    value = StudentID,
                    onValueChange = { StudentID = it },
                    label = { Text("StudentID") }
                )
                TextField(
                    value = StudentName,
                    onValueChange = { StudentName = it },
                    label = { Text("StudentName") }
                )
                TextButton(onClick = {
                    // Assuming you want to concatenate StudentID and StudentName before passing to onAddButtonClick
                    val combinedText = "$StudentID $StudentName"
                    onAddButtonClick(combinedText)
                }) {
                    Text("Add")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    StudentTheme {
        StudentApp()
    }
}
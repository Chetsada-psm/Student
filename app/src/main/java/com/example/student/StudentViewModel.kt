package com.example.student

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel(){
    var data = mutableStateListOf<StudentModel>()

    fun addStudent(StudentName:String, StudentID:String){
        //count data
        var count:Int = data.count()
        data.add(
             StudentModel(count++,StudentName,StudentID))
    }



}
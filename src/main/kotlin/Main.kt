package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


data class Doctor(
    val name: String, val avgConsultTime: Int
)

fun main() {
    val scanner = Scanner(System.`in`)
    val doctors = mutableListOf<Doctor>()
    var stopInputDoctor = ""
    while (stopInputDoctor != "d") {
        print("Enter doctor's name or 'd' to stop: ")
        stopInputDoctor = scanner.nextLine()
        if (stopInputDoctor != "d") {
            print("Enter average time for ${stopInputDoctor}: ")
            val avgTime = scanner.nextInt()
            scanner.nextLine()
            doctors.add(Doctor(stopInputDoctor, avgTime))
        }
    }

    print("Enter patient queue position: ")
    val patientQueuePosition = scanner.nextInt()

    println("=====================================")
    println("total doctor count ${doctors.size}")
    println(
        "waiting time for queue ${patientQueuePosition} is  ${
            getWaitingTime(
                doctors,
                patientQueuePosition
            )
        } minute "
    )

}

fun getWaitingTime(doctors: List<Doctor>, queuePosition: Int): Int {
    val allDoctor = doctors.size
    val patientsBeforePatient = queuePosition - 1
    val patientsPerDoctor = patientsBeforePatient / allDoctor
    val remainingPatients = patientsBeforePatient % allDoctor

    val lastDoctorIndex = if (remainingPatients == 0) allDoctor - 1 else remainingPatients - 1

    return if (remainingPatients != 0) {
        val totalpatientCount = patientsPerDoctor + 1
        val doctorAvaTime = doctors.get(lastDoctorIndex).avgConsultTime
        val totaWaitiogtime = doctorAvaTime * totalpatientCount
        totaWaitiogtime
    } else {
        doctors[lastDoctorIndex].avgConsultTime * patientsPerDoctor
    }

}



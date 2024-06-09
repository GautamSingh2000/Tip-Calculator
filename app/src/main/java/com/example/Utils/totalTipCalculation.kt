package com.example.Utils

fun TotalTipCalculation(
    tipPercentage: Int,
    totalBill: Double
): Double {
    return if(totalBill > 1 && totalBill.toString().isNotEmpty()) (totalBill * tipPercentage)/100 else 0.0
}


fun TotalPerPersionTipCalculation(
    tipPercentage: Int,
    totalBill: Double,
    spliInPersion: Int
):Double{
    val bill = TotalTipCalculation(tipPercentage = tipPercentage,totalBill = totalBill) + totalBill
    return (bill / spliInPersion)
}
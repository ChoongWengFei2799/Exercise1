package com.example.exercise1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.exercise1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonCalculate.setOnClickListener {
            calculateAll(it)
        }

        binding.buttonReset.setOnClickListener {
            resetAll(it)
        }
    }

    private fun calculateAll(view:View) {
        binding.apply {
            val loan = binding.textViewLoan
            val interest = binding.textViewInterest
            val monthlyRepayment = binding.textViewMonthlyRepayment

            loan.text = "Loan: " + (calculateLoan()).toString()
            interest.text = "Interest: " + (calculateInterest()).toString()
            monthlyRepayment.text = "Monthly Repayment: " + (calculateMonthlyRepayment()).toString()
        }
    }

    private fun calculateLoan(): Double {
        binding.apply {
            val carPrice = binding.editTextCarPrice.text.toString().toDouble()
            val downPayment = binding.editTextDownPayment.text.toString().toDouble()

            return carPrice - downPayment
        }
    }

    private fun calculateInterest(): Double {
        binding.apply {
            val loanPeriod = binding.editTextLoanPeriod.text.toString().toDouble()
            val interestRate = binding.editTextInterestRate.text.toString().toDouble()

            return calculateLoan() * loanPeriod * interestRate / 100
        }
    }

    private fun calculateMonthlyRepayment(): Double {
        binding.apply {
            val loanPeriod = binding.editTextLoanPeriod.text.toString().toDouble()

            return (calculateLoan() + calculateInterest()) / loanPeriod / 12
        }
    }

    private fun resetAll(view:View) {
        binding.apply {
            val carPrice = binding.editTextCarPrice
            val downPayment = binding.editTextDownPayment
            val loanPeriod = binding.editTextLoanPeriod
            val interestRate = binding.editTextInterestRate

            carPrice.setText("")
            downPayment.setText("")
            loanPeriod.setText("")
            interestRate.setText("")

            val loan = binding.textViewLoan
            val interest = binding.textViewInterest
            val monthlyRepayment = binding.textViewMonthlyRepayment

            loan.text = "Loan: "
            interest.text = "Interest: "
            monthlyRepayment.text = "Monthly Repayment: "
        }
    }
}

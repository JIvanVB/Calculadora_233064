package com.example.calculadora_233064

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val laEcuacion = StringBuilder("")

        val ecuacion = findViewById<TextView>(R.id.textView)

        ecuacion.text=laEcuacion

        findViewById<Button>(R.id.button0).setOnClickListener { laEcuacion.append("0"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button1).setOnClickListener { laEcuacion.append("1"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button2).setOnClickListener { laEcuacion.append("2"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button3).setOnClickListener { laEcuacion.append("3"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button4).setOnClickListener { laEcuacion.append("4"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button5).setOnClickListener { laEcuacion.append("5"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button6).setOnClickListener { laEcuacion.append("6"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button7).setOnClickListener { laEcuacion.append("7"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button8).setOnClickListener { laEcuacion.append("8"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.button9).setOnClickListener { laEcuacion.append("9"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonPor).setOnClickListener { laEcuacion.append("*"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonMenos).setOnClickListener { laEcuacion.append("-"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonMas).setOnClickListener { laEcuacion.append("+"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonDiv).setOnClickListener { laEcuacion.append("/"); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonPunto).setOnClickListener { laEcuacion.append("."); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonC).setOnClickListener { laEcuacion.clear(); ecuacion.text=laEcuacion.toString() }
        findViewById<Button>(R.id.buttonIgual).setOnClickListener { ecuacion.text=calcularResultado(laEcuacion) }

    }

    private fun calcularResultado(laEcuacion: StringBuilder): String {
        if (laEcuacion.isEmpty()) return ""
        if(!laEcuacion.first().isDigit() && !laEcuacion.last().isDigit()) return "ERROR"
        var stack = mutableListOf<String>()
        var nums = StringBuilder()
        laEcuacion.forEachIndexed { index, c ->
            when  {
                c.isDigit() -> nums.append(c)
                c == '.' && laEcuacion[index+1].isDigit()-> nums.append(c)
                c in "+-*/" -> {stack.add(nums.toString()); stack.add(c.toString()); nums.clear()}
                else -> return "ERROR"
            }
        }; stack.add(nums.toString())

        var n=stack[0].toDouble();stack.removeAt(0);var desi="";
        stack.forEach {
            if(desi=="") desi=it
                else when{
                    desi=="+" -> {n+=it.toDouble();desi=""}
                    desi=="-" -> {n-=it.toDouble();desi=""}
                    desi=="*" -> {n*=it.toDouble();desi=""}
                    desi=="/" -> {n/=it.toDouble();desi=""}
                }
            println(n)
        }
        return n.toString()
    }
}
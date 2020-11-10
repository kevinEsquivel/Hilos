package mx.tecnm.tepic.ladm_u2_hilottarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var hil = hilo(this)
    var ContadorHilo =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciar.setOnClickListener {
            try{
                hil.start()
            }catch(e: Exception){
                AlertDialog.Builder(this)
                    .setMessage("El Hilo No Se Puede Volver A Ejecutar ")
                    .setTitle("Atención")
                    .setPositiveButton("ok"){d, i-> 0}
            }
        }
        btnPausar.setOnClickListener {
            hil.pausar()
        }
        btnReiniciar.setOnClickListener {
            hil.reiniciar()
        }
        btnDetener.setOnClickListener {
            hil.Detener()
        }


    }
}
class hilo(p:MainActivity) :Thread() {
    var puntero = p
    var detener = true
    var despausado = true//si estuviera en false significaria que esta pausadoe el hilo
    var reinicio = false// si esta en false no esta reiniciado

    fun Detener() { detener = false }
    fun pausar() { despausado = !despausado }
    fun reiniciar() { reinicio = true }

    override fun run() {
        super.run()

        while (detener) {
            if (despausado == true) {
                puntero.ContadorHilo++
                puntero.runOnUiThread {
                    puntero.txv1.text = "Hilo:" + puntero.ContadorHilo
                }
                if (reinicio){
                    puntero.ContadorHilo = 0

                    reinicio = false
                }
            }

            sleep(500)
        }
    }
}


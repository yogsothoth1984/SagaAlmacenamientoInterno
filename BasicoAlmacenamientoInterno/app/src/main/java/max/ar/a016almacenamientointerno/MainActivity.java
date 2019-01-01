package max.ar.a016almacenamientointerno;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button agregarBoton;
    private EditText editMultiLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarBoton=findViewById(R.id.botonAgregarId);
        editMultiLinea=findViewById(R.id.editMultiLineaId);

        String[]archivos= fileList();
        /**Obtenemos la lista de todos los archivos creados por la Activity*/
        if (existe(archivos,"notas.txt"))
            try{
                InputStreamReader objInputStreamReader=new InputStreamReader(openFileInput("notas.txt"));
                /**crear un objeto de la clase InputStreamReader y al constructor de dicha clase le
                 *  pasamos el dato devuelto por el método openFileInput*/
                BufferedReader objBufferReader= new BufferedReader(objInputStreamReader);
                /**Creamos un objeto de la clase BufferedReader y le pasamos al constructor la
                 * referencia del objeto de la clase InputStreamReader*/
                String linea= objBufferReader.readLine();/**Leemos la primer línea del archivo de texto*/
                String todo= "";/**Inicializamos un String vacío*/
                while (linea !=null){
                    todo= todo+linea+"\n";/**Lo concatenamos al String junto a un salto de línea*/
                    linea= objBufferReader.readLine();
                }
                objBufferReader.close();
                objInputStreamReader.close();
                editMultiLinea.setText(todo);
            } catch (IOException e){
            }
    }
    private boolean existe(String[] archivos, String archbusca){
        for (int f=0;f< archivos.length;f++)
            if (archbusca.equals(archivos[f]))
                return true;
            return false;
    }
    public void grabar(View view){
        try{
            OutputStreamWriter archivoWriter= new OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE));
            /**Creamos un objeto de la clase OutputStreamWriter y al constructor de dicha clase
             * le enviamos el dato que retorna el método openFileOutput propio de la clase ActionBarActivity
             * que le pasamos como parámetro el nombre del archivo de texto y el modo de apertura.*/
            archivoWriter.write(editMultiLinea.getText().toString());
            archivoWriter.flush();
            /**método flush para que vuelque todos los datos que pueden haber quedado en el buffer*/
            archivoWriter.close();
            /**procedemos al cerrado del archivo*/
        } catch (IOException e){
        }
        Toast t=Toast.makeText(this,"Los datos fueron grabados",Toast.LENGTH_LONG);
        t.show();
        finish();
    }
}

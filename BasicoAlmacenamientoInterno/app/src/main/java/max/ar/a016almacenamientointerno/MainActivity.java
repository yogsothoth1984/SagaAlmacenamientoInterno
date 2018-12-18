package max.ar.a016almacenamientointerno;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                InputStreamReader archivoReader=new InputStreamReader(openFileInput("notas.txt"));
            }
    }
    public void grabar(View view){
        try{
            OutputStreamWriter archivoWriter= new OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE));
            /**Creamos un objeto de la clase OutputStreamWriter y al constructor de dicha clase
             * le enviamos el dato que retorna el método openFileOutput propio de la clase ActionBarActivity
             * que le pasamos como parámetro el nombre del archivo de texto y el modo de apertura.*/
            archivoWriter.write(editMultiLinea.getText().toString());
            archivoWriter.flush();
            archivoWriter.close();
        } catch (IOException e){
        }
        Toast t=Toast.makeText(this,"Los datos fueron grabados",Toast.LENGTH_LONG);
        t.show();
        finish();
    }
}

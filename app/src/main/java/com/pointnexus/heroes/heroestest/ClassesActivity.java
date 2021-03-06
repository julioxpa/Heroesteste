package com.pointnexus.heroes.heroestest;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pointnexus.heroes.heroestest.Models.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClassesActivity extends AppCompatActivity {

    ListView listViewClasses;
    Button btnProcurar;
    TextView txtId;
    EditText editText;
    ProgressDialog pd;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //GsonConverterFactory para converter diretamente json data para objeto
                .build();

        api = retrofit.create(Api.class);

        //Instancia Botoes
        btnProcurar = (Button) findViewById(R.id.btnProcurar);
        txtId = (TextView) findViewById(R.id.txtId);
        editText = (EditText) findViewById(R.id.edIdHero);
        listViewClasses = (ListView) findViewById(R.id.listViewClasses);

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurarClasse();
            }
        });


        //Mostra Progress Dialog
        pd = new ProgressDialog(ClassesActivity.this);
        pd.setMessage("carregando");
        pd.show();

        //ALIMENTA LISTVIEW
        pegarClasses();
    }

        public void procurarClasse() {
            //PEGA ID DIGITADO
            String classeId = editText.getText().toString();

            //CHAMA RESQUEST DA API PARA PEGAR RESPONSE COM ID DEFINIDO
            Call<Classes> call = api.pegarClasses(classeId);

            call.enqueue(new Callback<Classes>() {
                @Override
                public void onResponse(Call<Classes> call, Response<Classes> response) {
                    pd.dismiss();
                    //MOSTRA RESULTADOS
                    Classes classepega = response.body();
                    if (classepega != null) {
                        txtId.setText("Classe: "+classepega.getName());
                    } else {
                        txtId.setText("Classe não encontrada");
                    }
                }

                @Override
                public void onFailure(Call<Classes> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    //Metodo pegar CLASSES
    private void pegarClasses() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //GsonConverterFactory para converter diretamente json data para objeto
                .build();

        Api api = retrofit.create(Api.class);

        //chama metodo da api para pegar response
        Call<List<Classes>> call = api.pegarClasses();

        call.enqueue(new Callback<List<Classes>>() {
            @Override
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {
                pd.dismiss();

                //Pega todos os resultados
                List<Classes> listaClasses = response.body();

                //String array para a listView
                String[] classes = new String[listaClasses.size()];

                //Inserir todos no array
                for (int i = 0; i < listaClasses.size(); i++) {
                    classes[i] = listaClasses.get(i).getName();
                }

                //mostrar array no listview
                listViewClasses.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        classes));

                listViewClasses.setBackgroundColor(Color.WHITE);

            }

            @Override
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

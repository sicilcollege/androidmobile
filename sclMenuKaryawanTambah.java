package e.com.scluasmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class sclMenuKaryawanTambah extends AppCompatActivity {
    Button sclSimpan;
    private static final String URL = "http://192.168.43.19/scluasmobile/insertkaryawan.php";
    //                          string baru            samain kayak php
    public static final String sclIDKaryawanTambah2 = "id_karyawan";
    public static final String sclNamaKaryawanTambah2 = "nama_karyawan";
    public static final String sclTelpKaryawanTambah2 = "telp_karyawan";
    public static final String sclAlamatKaryawanTambah2 = "alamat_karyawan";
    public static final String sclPasswordKaryawanTambah2 = "password_karyawan";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenukaryawantambah);
        sclSimpan=findViewById(R.id.simpan);
        sclSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
            }
        });
    }
    public void SendData(){
        //              string baru                                         samain kayak xml
        final EditText sclIDKaryawanTambah3 = (EditText)findViewById(R.id.sclIDKaryawanTambah);
        final EditText sclNamaKaryawanTambah3 = (EditText)findViewById(R.id.sclNamaKaryawanTambah);
        final EditText sclTelpKaryawanTambah3 = (EditText)findViewById(R.id.sclTelpKaryawanTambah);
        final EditText sclAlamatKaryawanTambah3 = (EditText)findViewById(R.id.sclAlamatKaryawanTambah);
        final EditText sclPasswordKaryawanTambah3 = (EditText)findViewById(R.id.sclPasswordKaryawanTambah);

        final String id_karyawan = sclIDKaryawanTambah3.getText().toString().trim();
        final String nama_karyawan = sclNamaKaryawanTambah3.getText().toString().trim();
        final String telp_karyawan = sclTelpKaryawanTambah3.getText().toString().trim();
        final String alamat_karyawan = sclAlamatKaryawanTambah3.getText().toString().trim();
        final String password_karyawan = sclPasswordKaryawanTambah3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(sclMenuKaryawanTambah.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(sclMenuKaryawanTambah.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>params = new HashMap<String, String>();
                params.put(sclIDKaryawanTambah2,id_karyawan);
                params.put(sclNamaKaryawanTambah2,nama_karyawan);
                params.put(sclTelpKaryawanTambah2,telp_karyawan);
                params.put(sclAlamatKaryawanTambah2,alamat_karyawan);
                params.put(sclPasswordKaryawanTambah2,password_karyawan);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

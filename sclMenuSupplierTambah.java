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

public class sclMenuSupplierTambah extends AppCompatActivity {
    Button sclSimpan;
    private static final String URL = "http://192.168.43.19/scluasmobile/insertsupplier.php";
    //                          string baru            samain kayak php
    public static final String sclIDSupplierTambah2 = "id_supplier";
    public static final String sclNamaSupplierTambah2 = "nama_supplier";
    public static final String sclTelpSupplierTambah2 = "telp_supplier";
    public static final String sclAlamatSupplierTambah2 = "alamat_supplier";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenusuppliertambah);
        sclSimpan = (Button)findViewById(R.id.simpan);
        sclSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
            }
        });
    }
    public void SendData(){
        //              string baru                                         samain kayak xml
        final EditText sclIDSupplierTambah3 = (EditText)findViewById(R.id.sclIDSupplierTambah);
        final EditText sclNamaSupplierTambah3 = (EditText)findViewById(R.id.sclIDSupplierTambah);
        final EditText sclTelpSupplierTambah3 = (EditText)findViewById(R.id.sclTelpSupplierTambah);
        final EditText sclAlamatSupplierTambah3 = (EditText)findViewById(R.id.sclAlamatSupplierTambah);

        final String id_supplier = sclIDSupplierTambah3.getText().toString().trim();
        final String nama_supplier = sclNamaSupplierTambah3.getText().toString().trim();
        final String telp_supplier = sclTelpSupplierTambah3.getText().toString().trim();
        final String alamat_supplier = sclAlamatSupplierTambah3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(sclMenuSupplierTambah.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(sclMenuSupplierTambah.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>params = new HashMap<String, String>();
                params.put(sclIDSupplierTambah2,id_supplier);
                params.put(sclNamaSupplierTambah2,nama_supplier);
                params.put(sclTelpSupplierTambah2,telp_supplier);
                params.put(sclAlamatSupplierTambah2,alamat_supplier);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

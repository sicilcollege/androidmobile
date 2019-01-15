package e.com.scluasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class sclMenuMaster extends AppCompatActivity {
    ImageButton sclIBKaryawan, sclIBBarang, sclIBSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //connect ke nama xml menu master
        setContentView(R.layout.sclmenumaster);

        //declare imagebutton
        sclIBKaryawan = (ImageButton)findViewById(R.id.IBKaryawan);
        sclIBBarang = (ImageButton)findViewById(R.id.IBBarang);
        sclIBSupplier = (ImageButton)findViewById(R.id.IBSupplier);

        //image button karyawan
        sclIBKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuMaster.this,sclMenuKaryawan.class);
                startActivity(pindah);

            }
        });

        //image button barang
        sclIBBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuMaster.this,sclMenuBarang.class);
                startActivity(pindah);

            }
        });

        //image button supplier
        sclIBSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuMaster.this,sclMenuSupplier.class);
                startActivity(pindah);

            }
        });


    }
}

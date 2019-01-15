package e.com.scluasmobile;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class sclMenuAwal extends AppCompatActivity {
    ImageButton sclIBMaster, sclIBTransaksi, sclIBLaporan;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //connect ke nama xml menuawal
        setContentView(R.layout.sclmenuawal);

        //declare image button
        sclIBMaster = (ImageButton)findViewById(R.id.IBMaster);
        sclIBTransaksi = (ImageButton)findViewById(R.id.IBTransaksi);
        sclIBLaporan = (ImageButton)findViewById(R.id.IBLaporan);

        //image button master
        sclIBMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuAwal.this,sclMenuMaster.class);
                startActivity(pindah);
            }
        });

        //image button transaksi
        sclIBTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuAwal.this,sclMenuTransaksi.class);
                startActivity(pindah);
            }
        });

        //image button
        sclIBLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuAwal.this,sclMenuLaporan.class);
                startActivity(pindah);
            }
        });


    }
}

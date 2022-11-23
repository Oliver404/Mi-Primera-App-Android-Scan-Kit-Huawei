package com.oliverbotello.example.scanqrnoviembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {
    private static int QR_SCAN_REQUEST = 201;
    // Componets
    private TextView txtvwContent;
    private Button btnScan;
    // Vars
    HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions.Creator()
            .setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE)
            .setViewType(1).create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        if (requestCode == QR_SCAN_REQUEST) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);

            if (obj != null)
                txtvwContent.setText(obj.getOriginalValue());
            else
                Toast.makeText(this, "Code empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        txtvwContent = findViewById(R.id.txtvw_content);
        btnScan = findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Validar si tengo permiso de la camara
        // si tengo el permiso
            // Intentar scanear el codigo QR
            ScanUtil.startScan(this, QR_SCAN_REQUEST, options);
        // sino
            // Solicitar los permisos
    }
}
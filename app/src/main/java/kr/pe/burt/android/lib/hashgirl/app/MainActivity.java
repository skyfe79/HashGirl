package kr.pe.burt.android.lib.hashgirl.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import kr.pe.burt.android.lib.hashgirl.HashGirl;
import kr.pe.burt.android.lib.hashgirl.OnURLClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = "Hello, I'm #Super Hash Girl^ in the World. You can hash #every^thing what you want. #I will hash you^";

        TextView before = (TextView)findViewById(R.id.before);
        before.setText(text);

        TextView after1 = (TextView)findViewById(R.id.after1);
        HashGirl
            .with(text)
            .grap("(#((\\w+ *)*)\\^)", "^")
            .click(new OnURLClickListener() {
                @Override
                public void onClickURL(String url) {
                    Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                }
            })
            .into(after1);

        TextView after2 = (TextView)findViewById(R.id.after2);
        HashGirl
                .with(text)
                .grap("(#((\\w+ *)*)\\^)", "^", "#")
                .underline()
                .color(Color.BLUE)
                .bgcolor(Color.WHITE)
                .click(new OnURLClickListener() {
                    @Override
                    public void onClickURL(String url) {
                        Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                    }
                })
                .into(after2);

    }
}

package com.example.multy_lab_8;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewAppWidgetConfigureActivity extends AppCompatActivity {
    private NewAppWidgetConfigureActivity context;
    private int widgetID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_app_widget_configure);
        setResult(RESULT_CANCELED);
        context = this;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            final AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            final EditText editText = findViewById(R.id.appwidget_text);
            Button button = findViewById(R.id.add_button);
            button.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
                PendingIntent pending= PendingIntent.getActivity(context, 0,intent, PendingIntent.FLAG_IMMUTABLE);
                views.setOnClickPendingIntent(R.id.appwidget_text, pending);
                widgetManager.updateAppWidget(widgetID, views);
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
                setResult(RESULT_OK, resultValue);
                finish();
            });
        }
    }
}
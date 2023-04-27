package com.example.proyectonuevo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class Widget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context,appWidgetManager,appWidgetIds);

        // Actualizar todos los widgets
        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, MenuPrincipal.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, PendingIntent.FLAG_MUTABLE);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            remoteViews.setOnClickPendingIntent(R.id.boton, pendingIntent);

            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = mdformat.format(Calendar.getInstance().getTime());
            remoteViews.setTextViewText(R.id.txtHora, strDate);

            if (MenuPrincipal.num_imagen == 0) {
                remoteViews.setImageViewResource(R.id.img_widget, R.drawable.imagen_0);
            } else if (MenuPrincipal.num_imagen == 1) {
                remoteViews.setImageViewResource(R.id.img_widget, R.drawable.imagen_1);
            } else {
                remoteViews.setImageViewResource(R.id.img_widget, R.drawable.imagen_2);
            }

            // Actualizar el widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
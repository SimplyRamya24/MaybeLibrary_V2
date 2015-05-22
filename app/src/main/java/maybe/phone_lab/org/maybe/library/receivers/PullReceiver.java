package maybe.phone_lab.org.maybe.library.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import maybe.phone_lab.org.maybe.library.utils.Constants;
import maybe.phone_lab.org.maybe.library.utils.Utils;

public class PullReceiver extends BroadcastReceiver {
    public PullReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();
        if (intentAction.equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)){
            Utils.debug("receive BOOT UP intent");
            Intent pullIntent = new Intent(Constants.PULL_INTENT);
            setRepeatAlarm(context, pullIntent);
        } else if (intentAction.equalsIgnoreCase(Constants.PULL_INTENT)) {
            Utils.debug("receive pull intent");
        } else {
            Utils.debug("receive unknown intent action: " + intentAction);
        }
    }

    private void setRepeatAlarm(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() + Constants.PULL_INTERVAL, Constants.PULL_INTERVAL, pendingIntent);
    }
}
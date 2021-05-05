package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CalculateRootsService extends IntentService {


    public CalculateRootsService() {
        super("CalculateRootsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;
        long timeStartMs = System.currentTimeMillis();
        long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
        if (numberToCalculateRootsFor <= 0) {
            Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
            return;
        }
    /*
    TODO:
     calculate the roots.
     check the time (using `System.currentTimeMillis()`) and stop calculations if can't find an answer after 20 seconds
     upon success (found a root, or found that the input number is prime):
      send broadcast with action "found_roots" and with extras:
       - "original_number"(long)
       - "root1"(long)
       - "root2"(long)
     upon failure (giving up after 20 seconds without an answer):
      send broadcast with action "stopped_calculations" and with extras:
       - "original_number"(long)
       - "time_until_give_up_seconds"(long) the time we tried calculating

      examples:
       for input "33", roots are (3, 11)
       for input "30", roots can be (3, 10) or (2, 15) or other options
       for input "17", roots are (17, 1)
       for input "829851628752296034247307144300617649465159", after 20 seconds give up

     */

        // O(sqrt(n)) solution
        long root1 = numberToCalculateRootsFor;
        long root2 = 1;

        for (long i = 2; i <= Math.sqrt(numberToCalculateRootsFor); i++) {
            if ((System.currentTimeMillis() - timeStartMs) >= 20000) {
                Intent bcIntent = new Intent("stopped_calculations");
                bcIntent.putExtra("original_number", numberToCalculateRootsFor);
                bcIntent.putExtra("time_until_give_up_seconds", (System.currentTimeMillis() / 1000L - timeStartMs / 1000L));
                sendBroadcast(bcIntent);
                return;
            }
            if (numberToCalculateRootsFor % i == 0) {
                root1 = i;
                root2 = numberToCalculateRootsFor / i;
                break;
            }
        }

        Intent bcIntent = new Intent("found_roots");
        bcIntent.putExtra("original_number", numberToCalculateRootsFor);
        bcIntent.putExtra("root1", root1);
        bcIntent.putExtra("root2", root2);
        bcIntent.putExtra("time", (System.currentTimeMillis() / 1000L - timeStartMs / 1000L));
        sendBroadcast(bcIntent);

    }
}
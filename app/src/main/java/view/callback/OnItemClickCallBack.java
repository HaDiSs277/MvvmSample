package view.callback;

import android.view.View;

/**
 * Created by hadis.t on 4/29/2018.
 */

public interface OnItemClickCallBack {
    void OnItemClicked(View itemView, int position);
    void OnItemLongClicked(int position);
}

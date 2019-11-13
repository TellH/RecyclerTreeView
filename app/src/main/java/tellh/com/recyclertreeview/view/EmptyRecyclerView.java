package tellh.com.recyclertreeview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;



/**
 * 带有空布局的EmptyRecyclerView
 */
public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;

    final private RecyclerView.AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };


    public EmptyRecyclerView(Context context) {
        super(context);
    }


    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {

            Adapter adapter = getAdapter();
            boolean emptyViewVisible = adapter.getItemCount() == 0;

            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            //setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;


        checkIfEmpty();
    }
}

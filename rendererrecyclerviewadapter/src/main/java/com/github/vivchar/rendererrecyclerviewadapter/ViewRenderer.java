package com.github.vivchar.rendererrecyclerviewadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Vivchar Vitaly on 1/10/17.
 */
public abstract class ViewRenderer <M extends ViewModel, VH extends RecyclerView.ViewHolder> {

	@NonNull
	private final Type mType;
	@NonNull
	private final Context mContext;

	public ViewRenderer(@NonNull final Class<M> type, @NonNull final Context context) {
		mType = type;
		mContext = context;
	}

	@NonNull
	protected Context getContext() {
		return mContext;
	}

	/**
	 * This method will be called for a partial bind if you override the {@link com.github.vivchar.rendererrecyclerviewadapter
	 * .RendererRecyclerViewAdapter.DiffCallback#getChangePayload(ViewModel,
	 * ViewModel)} method
	 *
	 * @param model    your a ViewModel
	 * @param holder   your a ViewHolder
	 * @param payloads your payload
	 */
	public void rebindView(@NonNull final M model, @NonNull final VH holder, @NonNull final List<Object> payloads) {
		bindView(model, holder);
	}

	/**
	 * This method will be called for a full bind.
	 *
	 * @param model  your a ViewModel
	 * @param holder your a ViewHolder
	 */
	public abstract void bindView(@NonNull M model, @NonNull VH holder);

	@NonNull
	public abstract VH createViewHolder(@Nullable ViewGroup parent);

	@NonNull
	public Type getType() {
		return mType;
	}

	@Nullable
	public ViewState createViewState(@NonNull final ViewModel model, @NonNull final VH holder) {
		return null;
	}

	@NonNull
	protected View inflate(@LayoutRes final int layoutID, @Nullable final ViewGroup parent, final boolean attachToRoot) {
		return LayoutInflater.from(getContext()).inflate(layoutID, parent, attachToRoot);
	}

	@NonNull
	protected View inflate(@LayoutRes final int layoutID, final @Nullable ViewGroup parent) {
		return inflate(layoutID, parent, false);
	}
}

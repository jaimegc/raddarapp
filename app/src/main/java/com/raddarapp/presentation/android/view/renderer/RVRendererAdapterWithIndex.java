package com.raddarapp.presentation.android.view.renderer;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.exception.NullRendererBuiltException;

import java.util.Collection;
import java.util.List;

/**
 * RecyclerView.Adapter extension created to work RendererBuilders and Renderer instances. Other
 * adapters have to use this one to show information into RecyclerView widgets.
 * <p>
 * This class is the heart of this library. It's used to avoid the library users declare a new
 * renderer each time they have to show information into a RecyclerView.
 * <p>
 * RVRendererAdapter has to be constructed with a LayoutInflater to inflate views, one
 * RendererBuilder to provide Renderer to RVRendererAdapter and one AdapteeCollection to
 * provide the elements to render.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class RVRendererAdapterWithIndex<T> extends RecyclerView.Adapter<RendererViewHolderWithIndex> {

    private final RendererBuilderWithIndex<T> rendererBuilder;
    private AdapteeCollection<T> collection;

    public RVRendererAdapterWithIndex(RendererBuilderWithIndex<T> rendererBuilder) {
        this(rendererBuilder, new ListAdapteeCollection<T>());
    }

    public RVRendererAdapterWithIndex(RendererBuilderWithIndex<T> rendererBuilder, AdapteeCollection<T> collection) {
        this.rendererBuilder = rendererBuilder;
        this.collection = collection;
    }

    @Override public int getItemCount() {
        return collection.size();
    }

    public T getItem(int position) {
        return collection.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setCollection(AdapteeCollection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("The AdapteeCollection configured can't be null");
        }

        this.collection = collection;
    }

    /**
     * Indicate to the RecyclerView the type of Renderer used to one position using a numeric value.
     *
     * @param position to analyze.
     * @return the id associated to the Renderer used to render the content given a position.
     */
    @Override public int getItemViewType(int position) {
        T content = getItem(position);
        return rendererBuilder.getItemViewType(content);
    }

    /**
     * One of the two main methods in this class. Creates a RendererViewHolder instance with a
     * Renderer inside ready to be used. The RendererBuilder to create a RendererViewHolder using the
     * information given as parameter.
     *
     * @param viewGroup used to create the ViewHolder.
     * @param viewType associated to the renderer.
     * @return ViewHolder extension with the Renderer it has to use inside.
     */
    @Override public RendererViewHolderWithIndex onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        rendererBuilder.withParent(viewGroup);
        rendererBuilder.withLayoutInflater(LayoutInflater.from(viewGroup.getContext()));
        rendererBuilder.withViewType(viewType);
        RendererViewHolderWithIndex viewHolder = rendererBuilder.buildRendererViewHolder();
        if (viewHolder == null) {
            throw new NullRendererBuiltException("RendererBuilder have to return a not null viewHolder");
        }
        return viewHolder;
    }

    /**
     * Given a RendererViewHolder passed as argument and a position renders the view using the
     * Renderer previously stored into the RendererViewHolder.
     *
     * @param viewHolder with a Renderer class inside.
     * @param position to render.
     */
    @Override public void onBindViewHolder(RendererViewHolderWithIndex viewHolder, int position) {
        T content = getItem(position);
        RendererWithIndex<T> renderer = viewHolder.getRenderer();
        if (renderer == null) {
            throw new NullRendererBuiltException("RendererBuilder have to return a not null renderer");
        }
        renderer.setContent(content);
        renderer.setPosition(position);
        updateRendererExtraValues(content, renderer, position);
        renderer.render();
    }

    /**
     * Add an element to the AdapteeCollection.
     *
     * @param element to add.
     * @return if the element has been added.
     */
    public boolean add(T element) {
        return collection.add(element);
    }

    /**
     * Remove an element from the AdapteeCollection.
     *
     * @param element to remove.
     * @return if the element has been removed.
     */
    public boolean remove(Object element) {
        return collection.remove(element);
    }

    /**
     * Add a Collection of elements to the AdapteeCollection.
     *
     * @param elements to add.
     * @return if the elements have been added.
     */
    public boolean addAll(Collection<? extends T> elements) {
        return collection.addAll(elements);
    }

    /**
     * Remove a Collection of elements to the AdapteeCollection.
     *
     * @param elements to remove.
     * @return if the elements have been removed.
     */
    public boolean removeAll(Collection<?> elements) {
        return collection.removeAll(elements);
    }

    /**
     * Remove all elements inside the AdapteeCollection.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Allows the client code to access the AdapteeCollection from subtypes of RendererAdapter.
     *
     * @return collection used in the adapter as the adaptee class.
     */
    protected AdapteeCollection<T> getCollection() {
        return collection;
    }

    /**
     * Empty implementation created to allow the client code to extend this class without override
     * getView method.
     * <p>
     * This method is called before render the Renderer and can be used in RendererAdapter extension
     * to add extra info to the renderer created like the position in the ListView/RecyclerView.
     *
     * @param content to be rendered.
     * @param renderer to be used to paint the content.
     * @param position of the content.
     */
    protected void updateRendererExtraValues(T content, RendererWithIndex<T> renderer, int position) {

    }

    /**
     * Provides a ready to use diff update for our adapter based on the implementation of the
     * standard equals method from Object.
     *
     * @param newList to refresh our content
     */
    public void diffUpdate(List<T> newList) {
        if (getCollection().size() == 0) {
            addAll(newList);
            notifyDataSetChanged();
        } else {
            DiffCallbacksWithIndex diffCallbacks = new DiffCallbacksWithIndex(collection, newList);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallbacks);
            clear();
            addAll(newList);
            diffResult.dispatchUpdatesTo(this);
        }
    }
}
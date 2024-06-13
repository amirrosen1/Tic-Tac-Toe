/**
 * RendererFactory class is used to build a renderer based on the type of the renderer requested.
 *
 * @author Amir Rosengarten
 */
public class RendererFactory {
    /**
     * Constructs a new RendererFactory.
     */
    public RendererFactory() {};

    /**
     * Builds a renderer based on the given type.
     *
     * @param type The type of renderer to build.
     * @param size The size of the renderer.
     * @return The renderer.
     */
    public Renderer buildRenderer(String type, int size) {
        Renderer render = null;
        switch (type) {
            case "console":
                render = new ConsoleRenderer(size);
                break;
            case "none":
                render = new VoidRenderer();
                break;
        }
        return render;
    }
}

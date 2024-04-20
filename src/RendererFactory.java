public class RendererFactory {
    public static final String NONE = "none";
    public static final String CONSOLE = "console";

    RendererFactory(){}

    /**
     *
     * @param type of renderer
     * @param size of board
     * @return renderer initizlized
     */
    public Renderer buildRenderer(String type, int size){
        String RType = type.toLowerCase();
        return switch (RType){
            case NONE -> new VoidRenderer();
            case CONSOLE -> new ConsoleRenderer(size);
            default -> null;
        };
    }
}

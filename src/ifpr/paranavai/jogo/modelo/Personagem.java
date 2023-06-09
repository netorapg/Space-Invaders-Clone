package ifpr.paranavai.jogo.modelo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWGamepadState;


public class Personagem extends ElementoGrafico {
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 360;
    private static final int POSICAO_INICIAL_EM_Y = 500;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;
    //private GLFWGamepadState state;

    public Personagem() {
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.deslocamentoEmX = 0;
        this.deslocamentoEmY = 0;
        setPosicaoEmX(POSICAO_INICIAL_EM_X);
        setPosicaoEmY(POSICAO_INICIAL_EM_Y);

        //this.state = GLFWGamepadState.create();
    }

    public void atualizar() {
       this.setPosicaoEmX((this.getPosicaoEmX() + this.getDeslocamentoEmX()));
       this.setPosicaoEmY((this.getPosicaoEmY() + this.getDeslocamentoEmY()));
    }
    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/spaceship.png");
        setImagem(loading.getImage());
        setAlturaImagem(getImagem().getWidth(null));
        setLarguraImagem(getImagem().getHeight(null));
        setPosicaoEmX(POSICAO_INICIAL_EM_X);
        setPosicaoEmY(POSICAO_INICIAL_EM_Y);
    }

    public void atirar() {
        int frenteDaNave = this.getPosicaoEmX() + this.getLarguraImagem() / 2;
        Tiro tiro = new Tiro(frenteDaNave, this.getPosicaoEmY());
        this.tiros.add(tiro);
    }

    public void superAtirar() {
        int frenteDaNave = this.getPosicaoEmX() + this.getLarguraImagem() / 2;
        SuperTiro superTiro = new SuperTiro(frenteDaNave, this.getPosicaoEmY());
        this.superTiros.add(superTiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return this.superTiros;
    }
    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = new ArrayList<SuperTiro>();
        this.superTiros = superTiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = new ArrayList<Tiro>();
        this.tiros = tiros;
    }

    public void mover (/*GLFWGamepadState state,*/ KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
        /*  if (GLFW.glfwGetGamepadState(GLFW.GLFW_JOYSTICK_1, state)) {

            float xAxisValue = state.axes(GLFW.GLFW_GAMEPAD_AXIS_LEFT_X);
            float yAxisValue = state.axes(GLFW.GLFW_GAMEPAD_AXIS_LEFT_Y);

            this.deslocamentoEmX = (int) (xAxisValue * DESLOCAMENTO);
            this.deslocamentoEmY = (int) (yAxisValue * DESLOCAMENTO);
        }*/
       
    }

    public void parar (/*GLFWGamepadState state,*/ KeyEvent tecla) {
          int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }
       
                
        /* if (GLFW.glfwGetGamepadState(GLFW.GLFW_JOYSTICK_1, state)) {
            float xAxisValue = state.axes(GLFW.GLFW_GAMEPAD_AXIS_LEFT_X);
            float yAxisValue = state.axes(GLFW.GLFW_GAMEPAD_AXIS_LEFT_Y);

            if (Math.abs(xAxisValue) < 0.2f) {
                this.deslocamentoEmX = 0;
            }
            if (Math.abs(yAxisValue) < 0.2f) {
                this.deslocamentoEmY = 0;
            }
        }*/
        
    }

    public void dispararTiro() {
        
            atirar();
    }

    public void dispararSuperTiro() {
        superAtirar();
    }

    public Rectangle getBounds() {
        return null;
    }

    public void setVisivel(boolean b) {
    }

    public void morrer() {
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }
}
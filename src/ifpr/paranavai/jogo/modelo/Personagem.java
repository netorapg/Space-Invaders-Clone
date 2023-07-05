package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWGamepadState;


public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 360;
    private static final int POSICAO_INICIAL_EM_Y = 500;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;
    //private GLFWGamepadState state;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.deslocamentoEmX = 0;
        this.deslocamentoEmY = 0;
        //this.state = GLFWGamepadState.create();
    }

    public void atualizar() {
        this.posicaoEmX += this.deslocamentoEmX;
        this.posicaoEmY += this.deslocamentoEmY;
    }
    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/spaceship.png");
        this.imagem = loading.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
        
        
    }

    public void atirar() {
        int frenteDaNave = this.posicaoEmX + this.larguraImagem / 2;
        Tiro tiro = new Tiro(frenteDaNave, this.posicaoEmY );
        this.tiros.add(tiro);
    }

    public void superAtirar() {
        int frenteDaNave = this.posicaoEmX + this.larguraImagem / 2;
        SuperTiro superTiro = new SuperTiro(frenteDaNave, this.posicaoEmY);
        this.superTiros.add(superTiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return this.superTiros;
    }
    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = new ArrayList<Tiro>();
        this.tiros = tiros;
    }

    public int getPositionX() {
        return posicaoEmX;
    }

    public void setPositionX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPositionY() {
        return posicaoEmY;
    }

    public void setPositionY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoX) {
        this.deslocamentoEmX = deslocamentoX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoY) {
        this.deslocamentoEmY = deslocamentoY;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int largura) {
        this.larguraImagem = largura;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int altura) {
        this.alturaImagem = altura;
    }

    public void mover (/*GLFWGamepadState state,*/ KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
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
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
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
    public int getPosicaoEmX() {
        return this.posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return this.posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
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
}
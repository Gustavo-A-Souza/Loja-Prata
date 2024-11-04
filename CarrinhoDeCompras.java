import java.util.ArrayList;

public class CarrinhoDeCompras {
    // Classe interna para representar um item no carrinho
    public static class Item {
        private String nome;
        private double preco;
        private int quantidade;

        public Item(String nome, double preco, int quantidade) {
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getPrecoTotal() {
            return preco * quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        @Override
        public String toString() {
            return nome + " - R$" + preco + " x " + quantidade + " = R$" + getPrecoTotal();
        }
    }

    // Lista de itens no carrinho
    private ArrayList<Item> itens;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar item ao carrinho
    public void adicionarItem(Item item) {
        for (Item i : itens) {
            if (i.getNome().equals(item.getNome())) {
                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                return;
            }
        }
        itens.add(item);
    }

    // Método para remover item do carrinho
    public void removerItem(String nome) {
        itens.removeIf(item -> item.getNome().equals(nome));
    }

    // Método para calcular o total do carrinho
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPrecoTotal();
        }
        return total;
    }

    // Método para listar itens no carrinho
    public void listarItens() {
        System.out.println("Itens no carrinho:");
        for (Item item : itens) {
            System.out.println(item);
        }
    }

    // Método main para demonstração
    public static void main(String[] args) {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        // Adicionando itens ao carrinho
        Item pulseiraPrata = new Item("Pulseira de Prata", 150.00, 1);
        Item anelPrata = new Item("Anel de Prata", 80.00, 2);

        carrinho.adicionarItem(pulseiraPrata);
        carrinho.adicionarItem(anelPrata);

        // Listando itens no carrinho
        carrinho.listarItens();

        // Removendo item
        carrinho.removerItem("Pulseira de Prata");

        // Listando itens novamente após remoção
        carrinho.listarItens();

        // Exibindo total
        System.out.println("Total: R$" + carrinho.calcularTotal());
    }
}
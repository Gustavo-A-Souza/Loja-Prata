import java.util.ArrayList;
import java.util.Optional;

public class CarrinhoDeCompras {

    // Classe interna para representar um item no carrinho
    public static class Item {
        private String nome;
        private double preco;
        private int quantidade;

        public Item(String nome, double preco, int quantidade) {
            if (preco < 0) throw new IllegalArgumentException("O preço não pode ser negativo.");
            if (quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
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

        public void setQuantidade(int quantidade) {
            if (quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
            this.quantidade = quantidade;
        }

        public double getPrecoTotal() {
            return preco * quantidade;
        }

        @Override
        public String toString() {
            return nome + " - R$" + String.format("%.2f", preco) + " x " + quantidade + " = R$" 
                   + String.format("%.2f", getPrecoTotal());
        }
    }

    // Lista de itens no carrinho
    private final ArrayList<Item> itens;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar ou atualizar item no carrinho
    public void adicionarItem(Item item) {
        Optional<Item> existente = itens.stream()
                                        .filter(i -> i.getNome().equalsIgnoreCase(item.getNome()))
                                        .findFirst();

        if (existente.isPresent()) {
            Item i = existente.get();
            i.setQuantidade(i.getQuantidade() + item.getQuantidade());
        } else {
            itens.add(item);
        }
    }

    // Método para remover item do carrinho
    public void removerItem(String nome) {
        boolean removido = itens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
        if (!removido) {
            System.out.println("Item não encontrado: " + nome);
        } else {
            System.out.println("Item removido: " + nome);
        }
    }

    // Método para calcular o total do carrinho
    public double calcularTotal() {
        return itens.stream()
                    .mapToDouble(Item::getPrecoTotal)
                    .sum();
    }

    // Método para listar itens no carrinho
    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            System.out.println("Itens no carrinho:");
            itens.forEach(System.out::println);
        }
    }

    // Método main para demonstração
    public static void main(String[] args) {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        try {
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
            System.out.println("Total: R$" + String.format("%.2f", carrinho.calcularTotal()));

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

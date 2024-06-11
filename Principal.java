import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
        public static void main(String[] args) {
                List<Funcionario> funcionarios = new ArrayList<>();
                funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),
                                "Operador"));
                funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"),
                                "Operador"));
                funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"),
                                "Coordenador"));
                funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"),
                                "Diretor"));
                funcionarios
                                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"),
                                                "Recepcionista"));
                funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),
                                "Operador"));
                funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"),
                                "Contador"));
                funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"),
                                "Gerente"));
                funcionarios
                                .add(new Funcionario("Heloise", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"),
                                                "Eletricista"));
                funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"),
                                "Gerente"));

                funcionarios.removeIf(funcionario -> funcionario.getNome().equals("Joao"));

                funcionarios.forEach(System.out::println);

                funcionarios.forEach(
                                funcionario -> funcionario
                                                .setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

                Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                                .collect(Collectors.groupingBy(Funcionario::getFuncao));

                funcionariosPorFuncao.forEach((funcao, lista) -> {
                        System.out.println("Funcao: " + funcao);
                        lista.forEach(System.out::println);
                });

                int[] mesesAniversario = { 10, 12 };
                List<Funcionario> aniversariantes = funcionarios.stream()
                                .filter(funcionario -> Arrays.stream(mesesAniversario)
                                                .anyMatch(mes -> funcionario.getDataNascimento()
                                                                .getMonthValue() == mes))
                                .collect(Collectors.toList());
                System.out.println("Aniversariantes dos meses 10 e 12:");
                aniversariantes.forEach(System.out::println);

                Funcionario maisVelho = funcionarios.stream()
                                .min(Comparator.comparing(funcionario -> funcionario.getDataNascimento()))
                                .orElse(null);
                System.out.println("Funcionário mais velho: " + maisVelho.getNome() +
                                ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

                System.out.println("Funcionários em ordem alfabética:");
                funcionarios.stream()
                                .sorted(Comparator.comparing(Funcionario::getNome))
                                .forEach(System.out::println);

                BigDecimal totalSalarios = funcionarios.stream()
                                .map(Funcionario::getSalario)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                System.out.println("Total dos salários: " + totalSalarios.toString().replace('.', ','));

                BigDecimal salarioMinimo = new BigDecimal("1212.00");
                System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
                funcionarios.forEach(funcionario -> {
                        BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2,
                                        RoundingMode.DOWN);
                        System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
                });

        }
}

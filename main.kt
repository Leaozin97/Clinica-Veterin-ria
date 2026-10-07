class Pet(
    val nome: String,
    val especie: String,
    val peso: Double,
    val ehAgressivo: Boolean,
    val alergias: String? = null 
)

class Servico(
    val descricao: String,
    val preco: Double
)

class Atendimento(
    val pet: Pet,
    val servicos: List<Servico>,
    val codigoStatus: Int,
    val observacoes: String? = null 
) {

    fun calcularTotal(): Double {
        var subtotal = 0.0
        for (servico in servicos) {
            subtotal += servico.preco
        }

        val taxaAgressividade = if (pet.ehAgressivo) 25.0 else 0.0
        return subtotal + taxaAgressividade
    }

    
    fun obterStatus(): String {
        return when (codigoStatus) {
            1 -> "Triagem"
            2 -> "Em Atendimento"
            3 -> "Pronto para Alta"
            else -> "Status Desconhecido"
        }
    }

    fun exibirComprovante() {
        println("==========================================")
        println("          COMPROVANTE DE ATENDIMENTO      ")
        println("==========================================")
        println("Pet: ${pet.nome} (${pet.especie}) - Peso: ${pet.peso}kg")
        
        
        val infoAlergias = pet.alergias ?: "Nenhuma alergia conhecida"
        println("Alergias: $infoAlergias")

        val infoObs = observacoes ?: "Sem observações cadastrais"
        println("Observações: $infoObs")

        println("------------------------------------------")
        println("Status do Atendimento: ${obterStatus()}")
        println("------------------------------------------")
        println("Serviços Realizados:")

        
        for (servico in servicos) {
            println(" - ${servico.descricao}: R$ %.2f".format(servico.preco))
        }

        if (pet.ehAgressivo) {
            println(" - Taxa de Manuseio (Agressividade): R$ 25,00")
        }

        println("------------------------------------------")
        println("VALOR TOTAL: R$ %.2f".format(calcularTotal()))
        println("==========================================\n")
    }
}


fun main() {
    val consulta = Servico("Consulta Clínica", 120.0)
    val vacina = Servico("Vacina V10", 85.0)
    val banho = Servico("Banho e Tosa", 60.0)

    
    val petThor = Pet(
        nome = "Thor",
        especie = "Cão",
        peso = 14.5,
        ehAgressivo = true,
        alergias = "Alergia a Dipirona"
    )

    val petLuna = Pet(
        nome = "Luna",
        especie = "Gato",
        peso = 4.2,
        ehAgressivo = false,
        alergias = null
    )

    val atendimento1 = Atendimento(
        pet = petThor,
        servicos = listOf(consulta, vacina),
        codigoStatus = 2,
        observacoes = "Pet necessita de focinheira na triagem"
    )

    val atendimento2 = Atendimento(
        pet = petLuna,
        servicos = listOf(consulta, banho),
        codigoStatus = 3
    )

    atendimento1.exibirComprovante()
    atendimento2.exibirComprovante()
}
